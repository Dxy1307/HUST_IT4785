package com.dxy.studentmanagerroom

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)
