package com.example.sehatinajaapp.ui.camera

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.sehatinajaapp.databinding.FragmentCameraBinding
import com.example.sehatinajaapp.ml.QuantizedModel
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.common.TensorProcessor
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import java.io.IOException
import java.nio.ByteBuffer
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors



class CameraFragment : Fragment() {
    private val mInputSize = 64
    private val mModelPath = "model_katarak_93.tflite"
    private val mLabelPath = "labels.txt"

    private lateinit var binding: FragmentCameraBinding
    private var imageCapture: ImageCapture? = null
    private var lens: Int = 0
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var currentActivity: CameraFragment
//    private lateinit var outputDirectory: File
    private lateinit var rotationMatrix: Matrix
    private lateinit var bitmapBuffer: Bitmap
    private lateinit var imageAnalyzer: ImageAnalysis
    //    private lateinit var classifier: Classifier
    //    private lateinit var imageClassfier: Classifier


    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        // Handle Permission granted/rejected
        var permissionGranted = true
        permissions.entries.forEach {
            if (it.key in REQUIRED_PERMISSIONS && it.value == false)
                permissionGranted = false
        }
        if (!permissionGranted) {
            Toast.makeText(requireContext(),
                "Permission request denied",
                Toast.LENGTH_SHORT).show()
        } else {
            startCamera()
        }
    }

    companion object {
        private const val TAG = "CameraXApp"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS =
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        @JvmStatic
        fun newInstance(): CameraFragment = CameraFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout using the binding class
        binding = FragmentCameraBinding.inflate(inflater, container, false)
//        currentActivity = activity as CameraFragment

        // Request camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            requestPermissions()
        }
        // Return the root view of the binding instance
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the listeners for take photo and video capture buttons
        binding.cameraButton.setOnClickListener { takePhoto() }
        binding.flipCameraButton.setOnClickListener { flipCamera() }

//        outputDirectory = currentActivity.getOutputDirectory()

//        imageClassfier = ImageClassifier(mModelPath, mLabelPath)
//        imageClassfier = Classifier(requireActivity().assets, mModelPath, mLabelPath, mInputSize)

        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun takePhoto() {
        // Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture ?: return

        // Create a time-stamped name and MediaStore entry
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis())

//        val photoFile = File(
//            outputDirectory,
//            android.icu.text.SimpleDateFormat(
//                FILENAME_FORMAT,
//                Locale.getDefault()
//            ).format(System.currentTimeMillis()) + ".jpg"
//        )
//        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile)
//            .build()

        imageCapture.takePicture(cameraExecutor, object :
            ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                //get bitmap from image
                val bitmap = imageProxyToBitmap(image)
                processImage(bitmap)
                super.onCaptureSuccess(image)
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
            }

        })
    }

    /**
     *  convert image proxy to bitmap
     *  @param image
     */
    private fun imageProxyToBitmap(image: ImageProxy): Bitmap {
        val planeProxy = image.planes[0]
        val buffer: ByteBuffer = planeProxy.buffer
        val bytes = ByteArray(buffer.remaining())
        buffer.get(bytes)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap? {
        return try {
            val inputStream = requireContext().contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            Log.e(TAG, "Failed to retrieve bitmap from URI: ${e.message}", e)
            null
        }
    }


    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }
            imageAnalyzer = ImageAnalysis.Builder()
                .setTargetResolution(Size(200, 200))
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()

            imageCapture = ImageCapture.Builder().build()
            // Select back camera as a default
//            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            // switch camera
            var cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
            if(lens == 1){
                cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            }

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture)

            } catch(exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun flipCamera() {
        if (lens == 0){
            lens = 1
        }else{
            lens = 0
        }
        startCamera()
    }

    private fun requestPermissions() {
        permissionLauncher.launch(REQUIRED_PERMISSIONS)
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
//            baseContext, it) == PackageManager.PERMISSION_GRANTED
            requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }


    // Image Processing and Classification
    // Image Processing and Classification
    // Image Processing and Classification
    // Image Processing and Classification
    // Image Processing and Classification
    // Image Processing and Classification
    // Image Processing and Classification

//    @SuppressLint("UnsafeOptInUsageError")
//    private fun toBitmap(image: ImageProxy): Bitmap? {
//        if (!::bitmapBuffer.isInitialized) {
//            rotationMatrix = Matrix()
//            rotationMatrix.postRotate(image.imageInfo.rotationDegrees.toFloat())
//            bitmapBuffer = Bitmap.createBitmap(
//                image.width, image.height, Bitmap.Config.ARGB_8888
//            )
//        }
//        val yuvToRgbConverter = YuvToRgbConverter(requireContext())
//        yuvToRgbConverter.yuvToRgb(image.image!!, bitmapBuffer)
//        return Bitmap.createBitmap(
//            bitmapBuffer,
//            0,
//            0,
//            bitmapBuffer.width,
//            bitmapBuffer.height,
//            rotationMatrix,
//            false
//        )
//    }

    private fun processImage(bitmap: Bitmap) {
        try {
            // Tensorflow representation of the image
            var tfImage = TensorImage(DataType.FLOAT32)
            // Loading the original android image to the tensorflow image
            tfImage.load(bitmap)

            // Processing the image
            // The model we build uses 224x224 images as an input so we need so resize the image
            val imageProcessor = ImageProcessor.Builder()
                .add(ResizeOp(64, 64, ResizeOp.ResizeMethod.BILINEAR))
                .build()
            tfImage = imageProcessor.process(tfImage)

            // Load "model.tflite" "Model" refers to the name before ".tflite". So if model was named "mymodel.tflite" we would write MyModel.newInstance(...)
            val model = QuantizedModel.newInstance(requireContext())

            // Apply normalization operator for image classification (a necessary step)
            val probabilityProcessor =
                TensorProcessor.Builder().add(NormalizeOp(0f, 255f)).build()

            // running classification
            val outputs =
                model.process(probabilityProcessor.process(tfImage.tensorBuffer))

            // getting the output
            val outputBuffer = outputs.outputFeature0AsTensorBuffer

            // after execution
            val result: Float = outputBuffer.getFloatArray().get(0)
            if (result > 0.8) {
                // means prediction was for category corresponding to 0
                binding.textResult.text = "Cataract!"
                Log.d("result", "Result is " + outputBuffer.getFloatArray().get(0))
            } else {
                // means prediction was for category corresponding to 1
                binding.textResult.text = "Normal!"
                Log.d("result", "Result is " + outputBuffer.getFloatArray().get(0))
            }
        } catch (e: Exception) {
            Log.d("sdf", "Exception is " + e.localizedMessage)
        }
    }
}
