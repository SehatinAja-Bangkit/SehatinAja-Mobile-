package com.example.sehatinaja.ui.camera

import android.Manifest
//import android.R
//import android.content.pm.PackageManager
//import android.os.Bundle
//import android.view.View
//import android.widget.ImageButton
//import android.widget.Toast
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.appcompat.app.AppCompatActivity
//import androidx.camera.core.AspectRatio
//import androidx.camera.core.Camera
//import androidx.camera.core.CameraSelector
//import androidx.camera.core.ImageCapture
//import androidx.camera.core.ImageCaptureException
//import androidx.camera.core.Preview
//import androidx.camera.lifecycle.ProcessCameraProvider
//import androidx.camera.view.PreviewView
//import androidx.core.content.ContextCompat
//import com.example.sehatinaja.R
//import com.example.sehatinaja.ui.tflite.Classifier
//import java.io.File
//import java.util.concurrent.ExecutionException
//import java.util.concurrent.Executors

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sehatinaja.R

//import android.support.v7.app.AppCompatActivity
//import com.google.tflite.imageclassification.sample.R

class CameraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        savedInstanceState ?: supportFragmentManager.beginTransaction()
            .replace(R.id.container, Camera2BasicFragment.newInstance())
            .commit()
    }
//    private var capture: ImageButton? = null
//    private var toggleFlash: ImageButton? = null
//    private var flipCamera: ImageButton? = null
//    private var previewView: PreviewView? = null
//    var cameraFacing = CameraSelector.LENS_FACING_BACK
//
//    private lateinit var classifier: Classifier
//
//    private val activityResultLauncher = registerForActivityResult<String, Boolean>(
//        ActivityResultContracts.RequestPermission()
//    ) { result ->
//        if (result) {
//            startCamera(cameraFacing)
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_camera)
//        previewView = findViewById<PreviewView>(R.id.cameraPreview)
//        capture = findViewById<ImageButton>(R.id.capture)
//        toggleFlash = findViewById<ImageButton>(R.id.toggleFlash)
//        flipCamera = findViewById<ImageButton>(R.id.flipCamera)
//        if (ContextCompat.checkSelfPermission(
//                this@CameraActivity,
//                Manifest.permission.CAMERA
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            activityResultLauncher.launch(Manifest.permission.CAMERA)
//        } else {
//            startCamera(cameraFacing)
//        }
//        flipCamera?.setOnClickListener(View.OnClickListener {
//            cameraFacing = if (cameraFacing == CameraSelector.LENS_FACING_BACK) {
//                CameraSelector.LENS_FACING_FRONT
//            } else {
//                CameraSelector.LENS_FACING_BACK
//            }
//            startCamera(cameraFacing)
//        })
//    }
//
//    fun startCamera(cameraFacing: Int) {
//        val aspectRatio = aspectRatio(previewView!!.width, previewView!!.height)
//        val listenableFuture = ProcessCameraProvider.getInstance(this)
//        listenableFuture.addListener({
//            try {
//                val cameraProvider =
//                    listenableFuture.get() as ProcessCameraProvider
//                val preview =
//                    Preview.Builder().setTargetAspectRatio(aspectRatio).build()
//                val imageCapture =
//                    ImageCapture.Builder()
//                        .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
//                        .setTargetRotation(windowManager.defaultDisplay.rotation).build()
//                val cameraSelector = CameraSelector.Builder()
//                    .requireLensFacing(cameraFacing).build()
//                cameraProvider.unbindAll()
//                val camera: Camera =
//                    cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
//                capture!!.setOnClickListener {
//                    if (ContextCompat.checkSelfPermission(
//                            this@CameraActivity,
//                            Manifest.permission.WRITE_EXTERNAL_STORAGE
//                        ) != PackageManager.PERMISSION_GRANTED
//                    ) {
//                        activityResultLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                    } else {
//                        takePicture(imageCapture)
//                    }
//                }
//                toggleFlash!!.setOnClickListener(View.OnClickListener { setFlashIcon(camera) })
//                preview.setSurfaceProvider(previewView!!.surfaceProvider)
//            } catch (e: ExecutionException) {
//                e.printStackTrace()
//            } catch (e: InterruptedException) {
//                e.printStackTrace()
//            }
//        }, ContextCompat.getMainExecutor(this))
//    }
//
//    fun takePicture(imageCapture: ImageCapture) {
//        val file = File(getExternalFilesDir(null), System.currentTimeMillis().toString() + ".jpg")
//        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(file).build()
//        imageCapture.takePicture(
//            outputFileOptions,
//            Executors.newCachedThreadPool(),
//            object : ImageCapture.OnImageSavedCallback {
//                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
//                    runOnUiThread {
//                        Toast.makeText(
//                            this@CameraActivity,
//                            "Image saved at: " + file.path,
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                    startCamera(cameraFacing)
//                }
//
//                override fun onError(exception: ImageCaptureException) {
//                    runOnUiThread {
//                        Toast.makeText(
//                            this@CameraActivity,
//                            "Failed to save: " + exception.message,
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                    startCamera(cameraFacing)
//                }
//            })
//    }
//
//    private fun setFlashIcon(camera: Camera) {
//        if (camera.getCameraInfo().hasFlashUnit()) {
//            if (camera.getCameraInfo().getTorchState().getValue() === 0) {
//                camera.getCameraControl().enableTorch(true)
//                toggleFlash!!.setImageResource(R.drawable.baseline_flash_off_24)
//            } else {
//                camera.getCameraControl().enableTorch(false)
//                toggleFlash!!.setImageResource(R.drawable.baseline_flash_on_24)
//            }
//        } else {
//            runOnUiThread {
//                Toast.makeText(
//                    this@CameraActivity,
//                    "Flash is not available currently",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//    }
//
//    private fun aspectRatio(width: Int, height: Int): Int {
//        val previewRatio = Math.max(width, height).toDouble() / Math.min(width, height)
//        return if (Math.abs(previewRatio - 4.0 / 3.0) <= Math.abs(previewRatio - 16.0 / 9.0)) {
//            AspectRatio.RATIO_4_3
//        } else AspectRatio.RATIO_16_9
//    }
}