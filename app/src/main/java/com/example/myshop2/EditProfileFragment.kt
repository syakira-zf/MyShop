package com.example.myshop2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myshop2.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    private val args: EditProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val provinces = resources.getStringArray(R.array.provinces)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, provinces)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerProvince.adapter = adapter

        // isi data lama
        binding.edtName.setText(args.name)
        binding.edtNim.setText(args.nim)
        val pos = provinces.indexOf(args.province)
        if (pos >= 0) binding.spinnerProvince.setSelection(pos)

        binding.btnSave.setOnClickListener {
            val name = binding.edtName.text.toString()
            val nim = binding.edtNim.text.toString()
            val province = binding.spinnerProvince.selectedItem.toString()

            findNavController().previousBackStackEntry?.savedStateHandle?.set("profileData", Triple(name, nim, province))
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
