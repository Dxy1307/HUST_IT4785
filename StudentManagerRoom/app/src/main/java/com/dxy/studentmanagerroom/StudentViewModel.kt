package com.dxy.studentmanagerroom

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import com.dxy.studentmanagerroom.StudentRepository
import com.dxy.studentmanagerroom.StudentDatabase
class StudentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: StudentRepository
    val allStudents: LiveData<List<Student>>

    init {
        val studentDao = StudentDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(studentDao)
        allStudents = repository.allStudents
    }

    fun insert(student: Student) = viewModelScope.launch {
        repository.insert(student)
    }

    fun update(student: Student) = viewModelScope.launch {
        repository.update(student)
    }

    fun delete(student: Student) = viewModelScope.launch {
        repository.delete(student)
    }
}
