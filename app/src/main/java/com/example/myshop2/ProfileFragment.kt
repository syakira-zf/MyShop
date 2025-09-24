package com.example.myshop2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myshop2.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private var name: String = "Ariel Tatum"
    private var nim: String = "NIM123456"
    private var province: String = "Ngawi"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateProfileUI()

        binding.btnEdit.setOnClickListener {
            val action = ProfileFragmentDirections
                .actionProfileFragmentToEditProfileFragment(name, nim, province)
            findNavController().navigate(action)
        }

        // ambil data balik dari edit
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Triple<String, String, String>>("profileData")
            ?.observe(viewLifecycleOwner) { data ->
                name = data.first
                nim = data.second
                province = data.third
                updateProfileUI()
            }
    }

    private fun updateProfileUI() {
        with(binding) {
            txtName.text = "Nama: $name"
            txtNim.text = "NIM: $nim"
            txtProvince.text = "Provinsi: $province"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
