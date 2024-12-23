package com.dxy.managefile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dxy.managefile.databinding.ItemsBinding
import java.io.File

class FileAdapter(
    private val files: List<File>,
    private val onClick: (File) -> Unit
) : RecyclerView.Adapter<FileAdapter.FileViewHolder>() {

    class FileViewHolder(private val binding: ItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(file: File, onClick: (File) -> Unit) {
            binding.fileName.text = file.name
            binding.root.setOnClickListener { onClick(file) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val binding = ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.bind(files[position], onClick)
    }

    override fun getItemCount(): Int = files.size
}