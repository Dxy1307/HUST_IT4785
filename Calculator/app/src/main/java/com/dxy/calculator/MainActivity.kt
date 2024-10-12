package com.dxy.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dxy.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isNewOp : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationIcon(R.drawable.baseline_wb_sunny_24)

        // Set sự kiện cho nút số
        binding.num0.setOnClickListener { appendToExpression("0") }
        binding.num1.setOnClickListener { appendToExpression("1") }
        binding.num2.setOnClickListener { appendToExpression("2") }
        binding.num3.setOnClickListener { appendToExpression("3") }
        binding.num4.setOnClickListener { appendToExpression("4") }
        binding.num5.setOnClickListener { appendToExpression("5") }
        binding.num6.setOnClickListener { appendToExpression("6") }
        binding.num7.setOnClickListener { appendToExpression("7") }
        binding.num8.setOnClickListener { appendToExpression("8") }
        binding.num9.setOnClickListener { appendToExpression("9") }
        binding.numDot.setOnClickListener { appendToExpression(".") }

        // Set sự kiện cho nút phép toán
        binding.actionAdd.setOnClickListener { appendToExpression("+") }
        binding.actionMinus.setOnClickListener { appendToExpression("-") }
        binding.actionMultiply.setOnClickListener { appendToExpression("*") }
        binding.actionDivide.setOnClickListener { appendToExpression("/") }

        // Xóa toàn bộ
        binding.clear.setOnClickListener {
            binding.answer.text = ""
            isNewOp = true
        }

        // Xóa ký tự cuối
        binding.C.setOnClickListener {
            val expression = binding.answer.text.toString()
            if (expression.isNotEmpty()) {
                binding.answer.text = expression.dropLast(1)
            }
        }

        // Bấm "="
        binding.actionEquals.setOnClickListener {
            calculateResult()
        }
    }

    // Hàm thêm số hoặc phép toán vào biểu thức
    private fun appendToExpression(value: String) {
        if (isNewOp) {
            binding.answer.text = ""
        }
        binding.answer.append(value)
        isNewOp = false
    }

    // Hàm xử lý tính toán
    private fun calculateResult() {
        try {
            val expression = binding.answer.text.toString()
            val result = evaluateExpression(expression)
            binding.answer.text = result.toString()
        } catch (e: Exception) {
            binding.answer.text = "Error"
        }
        isNewOp = true
    }

    // Hàm tính toán biểu thức
    private fun evaluateExpression(expression: String): Int {
        val numbers = mutableListOf<Int>()
        val operators = mutableListOf<Char>()

        var currentNumber = ""

        // Tách số và phép toán ra
        for (char in expression) {
            if(char.isDigit() || char == '.') {
                currentNumber += char
            } else {
                numbers.add(currentNumber.toInt())
                currentNumber = ""
                operators.add(char)
            }
        }
        
        // Thêm số cuối cùng vào danh sách
        if (currentNumber.isNotEmpty()) {
            numbers.add(currentNumber.toInt())
        }

        // Xử lý nhân và chia trước
        var i = 0
        while(i < operators.size) {
            if(operators[i] == '*' || operators[i] == '/') {
                val left = numbers[i]
                val right = numbers[i+1]
                val result = if (operators[i] == '*') left * right else left / right

                // Thay thế số ở vị trí hiện tại bằng kết quả và xóa số tiếp theo
                numbers[i] = result
                numbers.removeAt(i+1) // xóa số đã tính
                operators.removeAt(i) // Xóa phép tính đã tính
                i-- // lùi
            }
            i++
        }

        // Xử lý cộng và trừ
        var result = numbers[0]
        i = 0
        while(i < operators.size) {
            when (operators[i]) {
                '+' -> result += numbers[i+1]
                '-' -> result -= numbers[i+1]
            }
            i++
        }

        return result
    }
}