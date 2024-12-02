package com.dxy.studentmanager_2

import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.dxy.studentmanager_2.databinding.FragmentStudentListBinding

class StudentListFragment : Fragment() {
    private lateinit var studentAdapter: ArrayAdapter<String>
    val studentList = mutableListOf("Nguyen Van A - MSSV: 001", "Tran Thi B - MSSV: 002")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStudentListBinding.inflate(inflater, container, false)

        studentAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, studentList)
        binding.studentListView.adapter = studentAdapter

        registerForContextMenu(binding.studentListView)

        // Lắng nghe dữ liệu trả về từ AddEditStudentFragment
        findNavController().currentBackStackEntry?.savedStateHandle
            ?.getLiveData<String>("studentResult")
            ?.observe(viewLifecycleOwner) { result ->
                val position = findNavController().currentBackStackEntry?.savedStateHandle?.get<Int>("editPosition")
                if (position != null) {
                    // Cập nhật mục hiện có
                    studentList[position] = result
                } else {
                    // Thêm mục mới
                    studentList.add(result)
                }
                studentAdapter.notifyDataSetChanged()
            }

        return binding.root
    }

    override fun onCreateContextMenu(
        menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        requireActivity().menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.menuEdit -> {
                // Truyền vị trí sinh viên cần chỉnh sửa
                val action = StudentListFragmentDirections.actionStudentListFragmentToAddEditStudentFragment()
                findNavController().navigate(action)

                findNavController().currentBackStackEntry?.savedStateHandle?.set("editPosition", info.position)
                true
            }
            R.id.menuRemove -> {
                studentList.removeAt(info.position)
                studentAdapter.notifyDataSetChanged()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}


