package com.dxy.studentmanager

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dxy.studentmanager.databinding.ActivityEditStudentBinding

class EditStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val student = intent.getSerializableExtra("student") as StudentModel
        val position = intent.getIntExtra("position", -1)

        binding.etStudentName.setText(student.studentName)
        binding.etStudentId.setText(student.studentId)

        binding.btnSave.setOnClickListener {
            val updatedStudent = StudentModel(
                binding.etStudentName.text.toString(),
                binding.etStudentId.text.toString()
            )
            val intent = intent
            intent.putExtra("student", updatedStudent)
            intent.putExtra("position", position)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        binding.btnCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}
