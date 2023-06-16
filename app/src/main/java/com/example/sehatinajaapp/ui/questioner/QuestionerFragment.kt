package com.example.sehatinajaapp.ui.questioner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sehatinajaapp.R
import com.example.sehatinajaapp.databinding.FragmentPenyakitUmumBinding
import com.example.sehatinajaapp.databinding.FragmentQuestionerBinding
import com.example.sehatinajaapp.ui.questioner.yesOrNo.dalam.Question1Fragment


class QuestionerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentQuestionerBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionerBinding.inflate(inflater,container,false)
        val root : View = binding.root

        setUpAction()
        return root
    }

    private fun setUpAction() {
        binding.answer1.setOnClickListener{
            val question1Fragment = Question1Fragment()
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment,question1Fragment )
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.answer2.setOnClickListener{
            val question1Fragment = Question1Fragment()
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment,question1Fragment )
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.answer3.setOnClickListener{
            val question1Fragment = Question1Fragment()
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment,question1Fragment )
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }


}