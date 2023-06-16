package com.example.sehatinajaapp.ui.screening

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sehatinajaapp.R
import com.example.sehatinajaapp.databinding.FragmentScreeningBinding
import com.example.sehatinajaapp.ui.camera.CameraFragment
import com.example.sehatinajaapp.ui.camera.CameraInstructionFragment
import com.example.sehatinajaapp.ui.home.HomeFragment
import com.example.sehatinajaapp.ui.questioner.QuestionerFragment


class ScreeningFragment : Fragment() {

    private var _binding: FragmentScreeningBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentScreeningBinding.inflate(inflater,container,false)
        val root : View = binding.root

        setUpAction()
        return root
    }
    fun setUpAction(){
        binding.backBtn3.setOnClickListener{
            val homeFragment = HomeFragment()
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment,homeFragment )
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        binding.btnScreening1.setOnClickListener{
            val penyakitUmumFragment = PenyakitUmumFragment()
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment,penyakitUmumFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
//
        }
        binding.btnScreening2.setOnClickListener{
            val questionerFragment = QuestionerFragment()
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment,questionerFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        binding.btnScreening3.setOnClickListener{
            val questionerFragment = QuestionerFragment()
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment,questionerFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        binding.btnScreening4.setOnClickListener{
            val cameraInstructionFragment = CameraInstructionFragment()
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment,cameraInstructionFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

//    companion object {
//
//
//    }

}
