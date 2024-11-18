package com.dxy.studentman

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.dxy.studentman.databinding.DialogEditStudentBinding

class EditStudentDialogFragment(
    private val student: StudentModel?,
    private val onSaveClicked: (StudentModel) -> Unit
) : DialogFragment() {

    private var _binding: DialogEditStudentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogEditStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (student != null) {
            binding.dialogTitle.text = "Edit Student"
            binding.editStudentName.setText(student.studentName)
            binding.editStudentId.setText(student.studentId)
        } else {
            binding.dialogTitle.text = "Add Student"
        }

        binding.buttonSave.setOnClickListener {
            val newName = binding.editStudentName.text.toString()
            val newId = binding.editStudentId.text.toString()

            if (newName.isNotBlank() && newId.isNotBlank()) {
                onSaveClicked(StudentModel(newName, newId))
                dismiss()
            }
        }


        binding.buttonCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()

        // Cập nhật kích thước của Dialog ở đây
        dialog?.window?.let { window ->
            val layoutParams = window.attributes
            // Chiều rộng Dialog sẽ chiếm 90% chiều rộng màn hình
            layoutParams.width = (resources.displayMetrics.widthPixels * 0.9).toInt()
            // Chiều cao tự động điều chỉnh theo nội dung
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            window.attributes = layoutParams

            // Căn giữa Dialog
            window.setGravity(Gravity.CENTER)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}