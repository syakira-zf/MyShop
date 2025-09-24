package com.example.myshop2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myshop2.databinding.FragmentCheckoutBinding


class CheckoutFragment : Fragment() {
    // buat var binding
    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // assign _binding
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        // set content view
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            // baca argumen yang dikirim dari home
            val args: CheckoutFragmentArgs by navArgs()
            var productName = args.productName

            // set text menjadi product
            txtProductName.text = productName

            // kalau edit text address di klik
            edtAddress.setOnClickListener {
                // buat action untuk navigasi ke address
                var action = CheckoutFragmentDirections.actionCheckoutFragmentToAddressFragment2()
                // cari navigation controller dari host
                findNavController().navigate(action)
            }

            // ambil data yang dikirim oleh addressFragment
            findNavController().currentBackStackEntry?.savedStateHandle?.let { handle ->
                handle.getLiveData<String>("address")
                    .observe(viewLifecycleOwner) { res ->
                        // edit address di update dengan data dari address fragment
                        edtAddress.setText(res)
                    }
            }

            btnDone.setOnClickListener {
                findNavController().navigateUp()
            }
                                    }
                        }

}