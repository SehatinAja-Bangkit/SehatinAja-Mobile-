package com.example.sehatinajaapp.ui.camera

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sehatinajaapp.R
import com.example.sehatinajaapp.databinding.FragmentCameraInstructionBinding
import com.example.sehatinajaapp.databinding.FragmentScreeningBinding
import com.example.sehatinajaapp.ui.home.HomeFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CameraInstructionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CameraInstructionFragment : Fragment() {
    private var _binding: FragmentCameraInstructionBinding? = null
    private val  binding  get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCameraInstructionBinding.inflate(inflater,container,false)
        val root : View = binding.root

        setUpAction()
        return root
    }
    fun setUpAction(){
        binding.btnAgree.setOnClickListener{
            val cameraBinding = CameraFragment()
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment,cameraBinding )
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        binding.backBtn.setOnClickListener{
            val homeFragment = HomeFragment()
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment,homeFragment )
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

}