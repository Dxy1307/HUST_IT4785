package com.dxy.managefile

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dxy.managefile.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)

        // Get the root or the selected directory
        val path = intent.getStringExtra("path")
        val root = if (path != null) File(path) else Environment.getExternalStorageDirectory()
        val files = root.listFiles()?.toList() ?: emptyList()

        binding.pathTextView.text = root.absolutePath

        // Set up RecyclerView with FileAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = FileAdapter(files) { file ->
            if (file.isDirectory) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("path", file.absolutePath)
                startActivity(intent)
            } else {
                if (file.extension == "txt") {
                    val intent = Intent(this, FileContentActivity::class.java)
                    intent.putExtra("path", file.absolutePath)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Không hỗ trợ file này", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}