package com.example.sehatinajaapp.ui.screening

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sehatinajaapp.R
import com.example.sehatinajaapp.databinding.FragmentPenyakitUmumBinding
import com.example.sehatinajaapp.databinding.FragmentScreeningBinding
import com.example.sehatinajaapp.ui.home.HomeFragment
import com.example.sehatinajaapp.ui.result.DiabetesResultFragment
import com.example.sehatinajaapp.ui.result.HipertensiResultFragment


class PenyakitUmumFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentPenyakitUmumBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPenyakitUmumBinding.inflate(inflater,container,false)
        val root : View = binding.root

        setUpAction()
        return root
    }

  fun setUpAction(){
      binding.backBtn.setOnClickListener{
          val homeFragment = HomeFragment()
          val fragmentTransaction = requireFragmentManager().beginTransaction()
          fragmentTransaction.replace(R.id.nav_host_fragment,homeFragment )
          fragmentTransaction.addToBackStack(null)
          fragmentTransaction.commit()
      }
      binding.btnDiabetes.setOnClickListener {
          val diabetesResultFragment = DiabetesResultFragment()
          val fragmentTransaction = requireFragmentManager().beginTransaction()
          fragmentTransaction.replace(R.id.nav_host_fragment,diabetesResultFragment )
          fragmentTransaction.addToBackStack(null)
          fragmentTransaction.commit()
      }
      binding.btnHipertensi.setOnClickListener{
          val hipertensiResultBinding = HipertensiResultFragment()
          val fragmentTransaction = requireFragmentManager().beginTransaction()
          fragmentTransaction.replace(R.id.nav_host_fragment,hipertensiResultBinding )
          fragmentTransaction.addToBackStack(null)
          fragmentTransaction.commit()
      }
  }
}