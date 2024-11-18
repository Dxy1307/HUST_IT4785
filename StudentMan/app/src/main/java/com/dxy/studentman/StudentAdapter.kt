package com.dxy.studentman

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.dxy.studentman.databinding.LayoutStudentItemBinding

class StudentAdapter(
    private var students: List<StudentModel>,
    private val onEditClick: (Int) -> Unit,
    private val onRemoveClick: (StudentModel) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(private val binding: LayoutStudentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(student: StudentModel, position: Int) {
            binding.textStudentName.text = student.studentName
            binding.textStudentId.text = student.studentId

            binding.imageEdit.setOnClickListener {
                onEditClick(position)
            }

            binding.imageRemove.setOnClickListener {
                showDeleteConfirmationDialog(student)
            }
        }

        private fun showDeleteConfirmationDialog(student: StudentModel) {
            val builder = AlertDialog.Builder(binding.root.context)
            builder.setTitle("Xác nhận")
                .setMessage("Bạn có chắc chắn muốn xóa sinh viên ${student.studentName}?")
                .setPositiveButton("Xóa") { _, _ -> onRemoveClick(student) }
                .setNegativeButton("Hủy", null)
                .show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = LayoutStudentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int = students.size

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position], position)
    }

    fun updateData(newStudents: List<StudentModel>) {
        students = newStudents
        notifyDataSetChanged()
    }
}