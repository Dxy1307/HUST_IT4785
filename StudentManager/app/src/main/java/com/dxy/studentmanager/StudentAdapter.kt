package com.dxy.studentmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class StudentAdapter(
    private val context: Context,
    private val students: MutableList<StudentModel>
) : BaseAdapter() {

    override fun getCount(): Int = students.size

    override fun getItem(position: Int): StudentModel = students[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            android.R.layout.simple_list_item_1,
            parent,
            false
        )
        val textView = view.findViewById<TextView>(android.R.id.text1)
        val student = getItem(position)
        textView.text = "${student.studentName} - ${student.studentId}"
        return view
    }
}
