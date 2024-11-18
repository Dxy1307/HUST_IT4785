package com.dxy.studentman

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dxy.studentman.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val studentViewModel: StudentViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val studentAdapter = StudentAdapter(mutableListOf(), onEditClick = { student ->
            showEditStudentDialog(student)
        }, onRemoveClick = { student ->
            removeStudentAndShowSnackbar(student)
        })

        binding.recyclerViewStudents.apply {
            adapter = studentAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        studentViewModel.students.observe(this) { students ->
            studentAdapter.updateData(students)
        }

        // Nút thêm sinh viên
        binding.btnAddNew.setOnClickListener {
            showAddStudentDialog()
        }
    }

    private fun showAddStudentDialog() {
        EditStudentDialogFragment(null) { newStudent ->
            studentViewModel.addStudent(newStudent)
        }.show(supportFragmentManager, "EditStudentDialog")
    }

    private fun showEditStudentDialog(position: Int) {
        val student = studentViewModel.students.value?.get(position)

        if (student != null) {
            EditStudentDialogFragment(student) { updatedStudent ->
                studentViewModel.updateStudent(updatedStudent, position)
            }.show(supportFragmentManager, "EditStudentDialog")
        }
    }

    private fun removeStudentAndShowSnackbar(student: StudentModel) {
        // Lưu sinh viên bị xóa vào viewModel
        val deletedStudent = student

        // Xóa sinh viên khỏi danh sách
        studentViewModel.removeStudent(student)

        // Hiển thị Snackbar với tùy chọn Undo
        Snackbar.make(binding.root, "${student.studentName} đã bị xóa", Snackbar.LENGTH_LONG)
            .setAction("Undo") {
                // Khôi phục sinh viên
                studentViewModel.restoreStudent(deletedStudent)
            }
            .show()
    }
}