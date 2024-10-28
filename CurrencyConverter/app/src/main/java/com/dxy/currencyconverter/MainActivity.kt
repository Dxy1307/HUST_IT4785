package com.dxy.currencyconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.dxy.currencyconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val currencies = listOf("USD ($)", "EUR (€)", "JPY (¥)", "VND (₫)", "GBP (£)")
    private val currencySymbols = mapOf("USD" to "$", "EUR" to "€", "JPY" to "¥", "VND" to "₫", "GBP" to "£")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.sourceCurrency.adapter = adapter
        binding.targetCurrency.adapter = adapter

        binding.sourceCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val selectedCurrency = currencies[position].split(" ")[0]
                binding.textSourceCurrency.text = currencySymbols[selectedCurrency]
                updateConversion()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        binding.targetCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: android.view.View?, p2: Int, p3: Long) {
                val selectedCurrency = currencies[p2].split(" ")[0]
                binding.textTargetCurrency.text = currencySymbols[selectedCurrency]
                updateConversion()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        binding.sourceAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                updateConversion()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    // Hàm cập nhật chuyển đổi
    private fun updateConversion() {
        val sourceAmount = binding.sourceAmount.text.toString().toDoubleOrNull() ?: 0.0
        val convertedAmount = sourceAmount * getConversionRate()

        binding.targetAmount.setText(String.format("%d", convertedAmount.toInt()))
    }

    // Hàm lấy tỷ giá chuyển đổi
    private fun getConversionRate(): Double {
        val sourceCurrency = binding.sourceCurrency.selectedItem.toString().split(" ")[0]
        val targetCurrency = binding.targetCurrency.selectedItem.toString().split(" ")[0]

        val conversionRates = mapOf(
            "USD" to mapOf("USD" to 1.0, "EUR" to 0.85, "JPY" to 110.0, "VND" to 25355.0, "GBP" to 0.73),
            "EUR" to mapOf("USD" to 1.18, "EUR" to 1.0, "JPY" to 129.0, "VND" to 27000.0, "GBP" to 0.86),
            "JPY" to mapOf("USD" to 0.0091, "EUR" to 0.0077, "JPY" to 1.0, "VND" to 210.0, "GBP" to 0.0067),
            "VND" to mapOf("USD" to 0.00003944, "EUR" to 0.000037, "JPY" to 0.0048, "VND" to 1.0, "GBP" to 0.000032),
            "GBP" to mapOf("USD" to 1.37, "EUR" to 1.16, "JPY" to 150.0, "VND" to 32000.0, "GBP" to 1.0)
        )

        return conversionRates[sourceCurrency]?.get(targetCurrency) ?: 0.0
    }
}