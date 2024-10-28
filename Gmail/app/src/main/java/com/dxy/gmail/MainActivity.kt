package com.dxy.gmail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dxy.gmail.adapter.EmailAdapter
import com.dxy.gmail.databinding.ActivityMainBinding
import com.dxy.gmail.model.Email

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var emailAdapter: EmailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Khởi tạo danh sách email mẫu với icon
        val emailList = listOf(
            Email("alice@e.com", "Hello from Alice! I hope you are doing well. Let's catch up soon.", "10:00 AM", R.drawable.blue_background, "A"),
            Email("bob@g.com", "Meeting at 2 PM to discuss the project updates and next steps.", "10:30 AM", R.drawable.green_background, "B"),
            Email("charlie@g.com", "Important update! Please review the attached documents before our meeting.", "11:00 AM", R.drawable.red_background, "C"),
            Email("diana@e.com", "Reminder: Your subscription will expire next week. Don't forget to renew it!", "11:30 AM", R.drawable.orange_background, "D"),
            Email("edward@e.com", "Great news! Your proposal has been approved. Next, we will schedule a call.", "12:00 PM", R.drawable.purple_background, "E"),
            Email("grace@e.com", "Invitation to the annual conference next month. Save the date!", "1:00 PM", R.drawable.blue_background, "G"),
            Email("hannah@e.com", "Follow-up on our last conversation regarding the budget proposal. Looking forward to your insights.", "1:30 PM", R.drawable.green_background, "H"),
            Email("ian@e.com", "Task reminder: Complete the documentation by end of day tomorrow.", "2:00 PM", R.drawable.red_background, "I"),
            Email("julia@e.com", "New opportunities: Check out the job postings on our website and apply!", "2:30 PM", R.drawable.orange_background, "J"),
            Email("kevin@e.com", "Weekly report: Please find attached the sales data for this week.", "3:00 PM", R.drawable.purple_background, "K")
        )

        // Thiết lập RecyclerView
        binding.recyclerViewEmails.layoutManager = LinearLayoutManager(this)
        emailAdapter = EmailAdapter(emailList)
        binding.recyclerViewEmails.adapter = emailAdapter
    }
}