package com.dxy.studentman

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {

    private val _students = MutableLiveData<List<StudentModel>>()
    val students: LiveData<List<StudentModel>> get() = _students

    private val removedStudents = mutableListOf<StudentModel>()

    init {
        // Khởi tạo danh sách sinh viên ban đầu
        _students.value = listOf(
            StudentModel("Nguyễn Văn An", "SV001"),
            StudentModel("Trần Thị Bảo", "SV002"),
            StudentModel("Lê Hoàng Cường", "SV003"),
            StudentModel("Phạm Thị Dung", "SV004"),
            StudentModel("Đỗ Minh Đức", "SV005"),
            StudentModel("Vũ Thị Hoa", "SV006"),
            StudentModel("Hoàng Văn Hải", "SV007"),
            StudentModel("Bùi Thị Hạnh", "SV008"),
            StudentModel("Đinh Văn Hùng", "SV009"),
            StudentModel("Nguyễn Thị Linh", "SV010"),
            StudentModel("Phạm Văn Long", "SV011"),
            StudentModel("Trần Thị Mai", "SV012"),
            StudentModel("Lê Thị Ngọc", "SV013"),
            StudentModel("Vũ Văn Nam", "SV014"),
            StudentModel("Hoàng Thị Phương", "SV015"),
            StudentModel("Đỗ Văn Quân", "SV016"),
            StudentModel("Nguyễn Thị Thu", "SV017"),
            StudentModel("Trần Văn Tài", "SV018"),
            StudentModel("Phạm Thị Tuyết", "SV019"),
            StudentModel("Lê Văn Vũ", "SV020")
        )
    }

    // Hàm thêm sinh viên
    fun addStudent(student: StudentModel) {
        val currentList = _students.value?.toMutableList() ?: mutableListOf() // Lấy danh sách hiện tại hoặc tạo mới nếu null
        currentList.add(student) // Thêm sinh viên mới vào danh sách
        _students.value = currentList // Cập nhật lại LiveData
    }

    // Hàm xóa sinh viên
    fun removeStudent(student: StudentModel) {
        _students.value?.let {
            val updatedList = it.toMutableList() // Tạo danh sách mutable từ danh sách hiện tại
            updatedList.remove(student) // Xóa sinh viên khỏi danh sách
            _students.value = updatedList // Cập nhật lại LiveData
        }
    }

    // Hàm cập nhật sinh viên
    fun updateStudent(updatedStudent: StudentModel, position: Int) {
        val currentList = _students.value.orEmpty()

        // Cập nhật sinh viên tại vị trí position
        _students.value = currentList.toMutableList().apply {
            this[position] = updatedStudent // Thay thế sinh viên ở vị trí position
        }
    }

    // Khôi phục sinh viên bị xóa
    fun restoreStudent(student: StudentModel) {
        // Thêm sinh viên đã xóa trở lại vào danh sách
        val currentList = _students.value.orEmpty()
        _students.value = currentList + student

        // Xóa sinh viên khỏi danh sách đã xóa
        removedStudents.remove(student)
    }
}