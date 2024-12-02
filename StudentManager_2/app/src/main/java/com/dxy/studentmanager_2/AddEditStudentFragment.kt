package com.dxy.studentmanager_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dxy.studentmanager_2.databinding.FragmentAddEditStudentBinding

// TODO: Rename parameter arguments, choose names that match
class AddEditStudentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAddEditStudentBinding.inflate(inflater, container, false)

        val position = findNavController().previousBackStackEntry?.savedStateHandle?.get<Int>("editPosition")
        if (position != null) {

            val studentList = (activity as StudentListFragment).studentList

            val student = studentList[position]
            val parts = student.split(" - MSSV: ")
            binding.etStudentName.setText(parts[0])
            binding.etStudentId.setText(parts[1])
        }

        binding.btnSave.setOnClickListener {
            val name = binding.etStudentName.text.toString()
            val id = binding.etStudentId.text.toString()
            if (name.isNotBlank() && id.isNotBlank()) {
                val result = "$name - MSSV: $id"
                findNavController().previousBackStackEntry?.savedStateHandle?.set("studentResult", result)
                findNavController().popBackStack()
            }
        }
        return binding.root
    }
}

