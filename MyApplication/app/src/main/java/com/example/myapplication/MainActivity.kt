package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val criarconta = findViewById<TextView>(R.id.criar_conta)

        criarconta.setOnClickListener {
            val abrirDashboardActivity =
                Intent(this, abrirDashboardActivity::class.java)
            startActivity(abrirDashboardActivity)
        }

    }

    }
}