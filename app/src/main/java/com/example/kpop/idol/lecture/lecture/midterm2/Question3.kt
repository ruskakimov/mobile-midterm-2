package com.example.kpop.idol.lecture.lecture.midterm2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Question3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val shortcut1Btn: Button = findViewById(R.id.shortcut1)

        shortcut1Btn.setOnClickListener {
            // Launch my currency converter
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("input_value", "100")
            intent.putExtra("input_currency", "USD")
            intent.putExtra("output_currency", "CAD")
            startActivity(intent)
        }

        val shortcut2Btn: Button = findViewById(R.id.shortcut2)

        shortcut2Btn.setOnClickListener {
            // Launch my calculator
            val intent = Intent(this, Question1::class.java)
            intent.putExtra("number1", "123")
            intent.putExtra("number2", "321")
            intent.putExtra("operation", "Add")
            startActivity(intent)
        }
    }
}