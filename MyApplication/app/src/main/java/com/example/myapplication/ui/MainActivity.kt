package com.example.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.utils.autenticar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        val criarConta = findViewById<TextView>(R.id.criar_conta)
        val buttonEntrar = findViewById<Button>(R.id.button_entrar)

        val editLoginEmail = findViewById<TextView>(R.id.edit_login_email)
        val editLoginSenha = findViewById<TextView>(R.id.edit_login_senha)

        buttonEntrar. setOnClickListener {
            val autenticou = autenticar(
                    editLoginEmail.text.toString(),
                    editLoginSenha.text.toString(),
                    this)

            if (autenticou) {
                val intent = Intent(
                        this,
                        DashboardActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuário ou senha incorreto", Toast.LENGTH_SHORT).show()
            }

        }

        criarConta.setOnClickListener {
          val abrirNovoUsuarioActivity =
              Intent(this, NovoUsuarioActivity::class.java)
          startActivity(abrirNovoUsuarioActivity)
       }

    }

}