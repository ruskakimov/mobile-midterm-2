package com.example.kpop.idol.lecture.lecture.midterm2

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val valueEdit: EditText = findViewById(R.id.value_edit)
        val inCurSpinner: Spinner = findViewById(R.id.in_cur_spinner)
        val outCurSpinner: Spinner = findViewById(R.id.out_cur_spinner)
        val convertButton: Button = findViewById(R.id.convert_button)
        val resultTxt: TextView = findViewById(R.id.result_txt)

        val currencyOptions = arrayOf("USD", "CAD", "WON")
        val priceInUSD = mapOf(
            "USD" to 1.0,
            "CAD" to 0.73,
            "WON" to 0.00072
        )

        // Populate spinners
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencyOptions)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        inCurSpinner.adapter = adapter1
        var selectedCurrencyIn = currencyOptions.get(0)

        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencyOptions)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        outCurSpinner.adapter = adapter2
        var selectedCurrencyOut = currencyOptions.get(1)
        outCurSpinner.setSelection(1)

        // Read extra data from intent
        val bundle: Bundle? = intent.extras
        bundle?.let {
            val inputValue = bundle.getString("input_value")
            val inputCurrency = bundle.getString("input_currency")
            val outputCurrency = bundle.getString("output_currency")

            val inIdx = currencyOptions.indexOf(inputCurrency)
            val outIdx = currencyOptions.indexOf(outputCurrency)

            valueEdit.setText(inputValue)
            inCurSpinner.setSelection(if (inIdx >= 0) inIdx else 0)
            outCurSpinner.setSelection(if (outIdx >= 0) outIdx else 0)
        }

        inCurSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedCurrencyIn = currencyOptions.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        outCurSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedCurrencyOut = currencyOptions.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        convertButton.setOnClickListener {
            val value = valueEdit.text.toString().toDoubleOrNull()
            if (value != null) {
                val usdInPrice = priceInUSD[selectedCurrencyIn]
                if (usdInPrice != null) {
                    val usdValue = value * usdInPrice
                    val usdOutPrice = priceInUSD[selectedCurrencyOut]
                    if (usdOutPrice != null) {
                        val result = usdValue / usdOutPrice

                        // round to last two digits after decimal point
                        resultTxt.text = String.format("%.2f", result)
                    }
                }
            }
        }
    }
}