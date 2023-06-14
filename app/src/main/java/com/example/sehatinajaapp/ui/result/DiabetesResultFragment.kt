package com.example.sehatinajaapp.ui.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sehatinajaapp.R
import com.example.sehatinajaapp.databinding.FragmentDiabetesResultBinding
import com.example.sehatinajaapp.databinding.FragmentScreeningBinding
import com.example.sehatinajaapp.ui.home.HomeFragment
import com.example.sehatinajaapp.ui.screening.ScreeningFragment


class DiabetesResultFragment : Fragment() {
    private var _binding : FragmentDiabetesResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiabetesResultBinding.inflate(inflater,container,false)
        val root : View = binding.root

        setUpAction()
        return root
    }

    private fun setUpAction() {
        binding.backBtn.setOnClickListener {
            val screeningFragment = ScreeningFragment()
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment, screeningFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }


}