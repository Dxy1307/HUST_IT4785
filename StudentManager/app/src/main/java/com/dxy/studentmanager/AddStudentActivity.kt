package com.dxy.studentmanager

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dxy.studentmanager.databinding.ActivityAddStudentBinding

class AddStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val studentName = binding.etStudentName.text.toString()
            val studentId = binding.etStudentId.text.toString()

            val student = StudentModel(studentName, studentId)
            val intent = intent
            intent.putExtra("student", student)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        binding.btnCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}
