package com.dxy.studentmanager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {

    // LiveData chứa danh sách sinh viên
    private val _students = MutableLiveData<MutableList<StudentModel>>(mutableListOf())
    val students: LiveData<MutableList<StudentModel>> get() = _students

    // Thêm sinh viên
    fun addStudent(student: StudentModel) {
        _students.value?.let {
            it.add(student)
            _students.value = it
        }
    }

    // Cập nhật sinh viên
    fun updateStudent(position: Int, updatedStudent: StudentModel) {
        _students.value?.let {
            it[position] = updatedStudent
            _students.value = it
        }
    }

    // Xóa sinh viên
    fun removeStudent(position: Int) {
        _students.value?.let {
            it.removeAt(position)
            _students.value = it
        }
    }
}
