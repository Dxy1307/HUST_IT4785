package com.dxy.findinlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dxy.findinlist.adapter.StudentAdapter
import com.dxy.findinlist.model.Student

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etSearch = findViewById<EditText>(R.id.etSearch)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Tạo danh sách sinh viên mẫu
        studentList = listOf(
            Student("Nguyen Van A", "20210001"),
            Student("Tran Thi B", "20210002"),
            Student("Le Minh C", "20210003"),
            Student("Pham Thi D", "20210004"),
            Student("Do Thanh E", "20210005"),
            Student("Ngo Quoc F", "20210006"),
            Student("Ly Hoang G", "20210007"),
            Student("Phan Anh H", "20210008"),
            Student("Nguyen Van I", "20210009"),
            Student("Tran Thi J", "20210010"),
            Student("Le Minh K", "20210011"),
            Student("Pham Thi L", "20210012"),
            Student("Do Thanh M", "20210013"),
            Student("Ngo Quoc N", "20210014"),
            Student("Ly Hoang O", "20210015"),
            Student("Phan Anh P", "20210016"),
            Student("Nguyen Van Q", "20210017"),
            Student("Tran Thi R", "20210018"),
            Student("Le Minh S", "20210019"),
            Student("Pham Thi T", "20210020"),
            Student("Do Thanh U", "20210021"),
            Student("Ngo Quoc V", "20210022"),
            Student("Ly Hoang W", "20210023"),
            Student("Phan Anh X", "20210024"),
            Student("Nguyen Van Y", "20210025"),
            Student("Tran Thi Z", "20210026"),
            Student("Le Minh AA", "20210027"),
            Student("Pham Thi AB", "20210028"),
            Student("Do Thanh AC", "20210029"),
            Student("Ngo Quoc AD", "20210030")
        )


        // Thiết lập RecyclerView với danh sách sinh viên
        studentAdapter = StudentAdapter(studentList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        // Thiết lập tìm kiếm khi người dùng nhập từ khóa
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val keyword = s.toString().trim()
                if (keyword.length > 2) {
                    filterList(keyword)
                } else {
                    studentAdapter.updateList(studentList)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    // Hàm lọc danh sách sinh viên dựa trên từ khóa
    private fun filterList(keyword: String) {
        val filteredList = studentList.filter {
            it.name.contains(keyword, ignoreCase = true) || it.mssv.contains(keyword)
        }
        studentAdapter.updateList(filteredList)
    }
}