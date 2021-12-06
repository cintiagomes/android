package com.example.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.myapplication.R
import com.example.myapplication.utils.calcularIdade
import com.example.myapplication.utils.convertBase64ToBitmap
import com.example.repository.PesagemRepository

class DashboardActivity : AppCompatActivity() {

    lateinit var tvNome: TextView
    lateinit var tvProfissao: TextView
    lateinit var tvImc: TextView
    lateinit var tvNcd: TextView
    lateinit var tvPeso: TextView
    lateinit var tvIdade: TextView
    lateinit var tvAltura: TextView
    lateinit var ivPerfil: ImageView
    lateinit var cardNovaPesagem: CardView
    lateinit var cardHistorico: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar!!.hide()

        tvNome = findViewById(R.id.tv_nome)
        tvNcd = findViewById(R.id.tv_dash_ncd)
        tvProfissao = findViewById(R.id.tv_profissao)
        tvAltura = findViewById(R.id.tv_dash_altura)
        tvPeso = findViewById(R.id.tv_dash_peso)
        ivPerfil = findViewById(R.id.iv_perfil)
        tvIdade = findViewById(R.id.tv_dash_idade)
        tvImc = findViewById(R.id.tv_dash_imc)
        cardNovaPesagem = findViewById(R.id.card_pesar_agora)
        cardHistorico = findViewById(R.id.card_historico)

        cardNovaPesagem.setOnClickListener {
            val intent = Intent(this, PesagemActivity::class.java)
            startActivity(intent)
        }

        cardHistorico.setOnClickListener {

            val intent = Intent(this, HistoricoActivity::class.java)
            startActivity(intent)

        }

        carregarDashbord()

    }

    private fun carregarDashbord() {

        val arquivo =
                getSharedPreferences(
                "usuario", MODE_PRIVATE)

        tvNome.text = arquivo.getString("nome", "")
        tvProfissao.text = arquivo.getString("profissao", "")
        tvAltura.text = arquivo.getFloat("altura", 0.0f).toString()
        tvIdade.text = calcularIdade(arquivo.getString("dataNascimento", "")!!).toString()

        val bitmap = convertBase64ToBitmap(arquivo.getString("fotoPerfil", "")!!)
        ivPerfil.setImageBitmap(bitmap)

    }

}