package com.example.kpop.idol.lecture.lecture.midterm2

import android.os.Bundle
import android.view.MenuItem
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

class Question1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        toolbar.setTitle("Calculator")
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Show back button

        val operationSpinner: Spinner = findViewById(R.id.operation_spinner)
        val number1EditText: EditText = findViewById(R.id.number_1)
        val number2EditText: EditText = findViewById(R.id.number_2)
        val calculateButton: Button = findViewById(R.id.calculate_btn)
        val resultTextView: TextView = findViewById(R.id.result_txt)

        val operationOptions = arrayOf("Add", "Subtract", "Multiply", "Divide")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, operationOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        operationSpinner.adapter = adapter

        var selectedOperation = operationOptions.get(0)

        // Read extra data from intent
        val bundle: Bundle? = intent.extras
        bundle?.let {
            val number1 = bundle.getString("number1")
            val number2 = bundle.getString("number2")
            val operation = bundle.getString("operation")

            val opIdx = operationOptions.indexOf(operation)

            selectedOperation = if (operation != null && opIdx >= 0) operation else "Add"
            operationSpinner.setSelection(if (opIdx >= 0) opIdx else 0)

            number1EditText.setText(number1)
            number2EditText.setText(number2)
        }

        operationSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedOperation = operationOptions.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        calculateButton.setOnClickListener {
            val number1 = number1EditText.text.toString().toDouble()
            val number2 = number2EditText.text.toString().toDouble()

            val result = when (selectedOperation) {
                "Add" -> number1 + number2
                "Subtract" -> number1 - number2
                "Multiply" -> number1 * number2
                "Divide" -> number1 / number2
                else -> 0.0
            }
            resultTextView.text = result.toString()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Go back to the previous activity
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item) // Keep default behavior
        }
    }
}