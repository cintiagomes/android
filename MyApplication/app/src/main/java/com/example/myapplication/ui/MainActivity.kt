package com.example.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        val criarConta = findViewById<TextView>(R.id.criar_conta)

        criarConta.setOnClickListener {
          val abrirNovoUsuarioActivity =
              Intent(this, NovoUsuarioActivity::class.java)
          startActivity(abrirNovoUsuarioActivity)
       }

    }

}