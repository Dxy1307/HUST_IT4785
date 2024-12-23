package com.dxy.managefile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dxy.managefile.databinding.ActivityFileContentBinding
import java.io.File

class FileContentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFileContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFileContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val filePath = intent.getStringExtra("path")
        if (filePath != null) {
            val file = File(filePath)
            binding.fileContent.text = file.readText()
        } else {
            binding.fileContent.text = "Không thể đọc file."
        }
    }
}