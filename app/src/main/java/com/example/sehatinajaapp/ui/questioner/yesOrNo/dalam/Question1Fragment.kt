package com.example.sehatinajaapp.ui.questioner.yesOrNo.dalam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sehatinajaapp.R
import com.example.sehatinajaapp.databinding.FragmentQuestion1Binding
import com.example.sehatinajaapp.databinding.FragmentQuestionerBinding
import com.example.sehatinajaapp.ui.result.TulangResultFragment
import com.example.sehatinajaapp.ui.result.TulangResutlActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Question1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Question1Fragment : Fragment() {
    private var _binding: FragmentQuestion1Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestion1Binding.inflate(inflater,container,false)
        val root : View = binding.root

        setUpAction()
        return root
    }

    private fun setUpAction() {
        binding.answerYes.setOnClickListener{
            val tulangResutlFragment= TulangResultFragment()
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment,tulangResutlFragment )
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }


}