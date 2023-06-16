package com.example.sehatinajaapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.example.sehatinajaapp.R
import com.example.sehatinajaapp.databinding.FragmentHomeBinding
import com.example.sehatinajaapp.ui.login.LoginActivity
import com.example.sehatinajaapp.ui.screening.ScreeningFragment


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root : View = binding.root

        setUpAction()
        return root


    }


    fun setUpAction(){

////        binding.user.setOnClickListener{
////            val activityLogin = LoginActivity()
////            val fragmentTransaction = requireFragmentManager().beginTransaction()
////            fragmentTransaction.replace(R.id.nav_host_fragment, activityLogin)
////            fragmentTransaction.addToBackStack(null)
////            fragmentTransaction.commit()
//        }
        binding.ibScreening.setOnClickListener{
            val fragmentScreening = ScreeningFragment()
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment, fragmentScreening)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }
//    override fun onClicked(view: View) {
//
//    }






//    companion object {
//        fun newInstance(param1: String, param2: String) =
//            HomeFragment().apply {
//                arguments = Bundle().apply {
////                    putString(ARG_PARAM1, param1)
////                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}