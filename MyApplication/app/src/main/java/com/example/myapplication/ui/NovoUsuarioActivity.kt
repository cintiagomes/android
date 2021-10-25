package com.example.myapplication.ui

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.model.Usuario
import com.example.myapplication.utils.convertStringToLocalDate
import java.time.LocalDate
import java.util.*

class NovoUsuarioActivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var editNome: EditText
    lateinit var editProfissao: EditText
    lateinit var editAltura: EditText
    lateinit var etData: EditText
    lateinit var radioFeminino: RadioButton
    lateinit var radioMasculino: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_usuario)

        editEmail = findViewById(R.id.edit_email)
        editSenha = findViewById(R.id.edit_senha)
        editNome = findViewById(R.id.edit_nome)
        editProfissao = findViewById(R.id.edit_profissao)
        editAltura = findViewById(R.id.edit_altura)
        etData = findViewById(R.id.et_data)
        radioFeminino = findViewById(R.id.radio_feminino)
        radioMasculino = findViewById(R.id.radio_masculino)

        supportActionBar!!.title = "Novo usuário"


        //Criar uma calendário
        val calendario = Calendar.getInstance()

        //Determinar os dados(dia, mês e ano) do calendário
        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        //Abrir o componente DatePicker
        val etDataNascimento = findViewById<EditText>(R.id.et_data)

        etDataNascimento.setOnClickListener {
            val dp = DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { _, _ano, _mes, _dia ->
                        etDataNascimento.setText("$_dia/${_mes + 1}/$_ano")
                    }, ano, mes, dia)

            dp.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (validarCampos()){
            //Criar o objeto usuario
            val nascimento = convertStringToLocalDate(etData.text.toString())
            val usuario =  Usuario(
                1,
                editNome.text.toString(),
                editEmail.text.toString(),
                editSenha.text.toString(),
                0,
                editAltura.text.toString().toDouble(),
                LocalDate.of(
                    nascimento.year,
                    nascimento.monthValue,
                    nascimento.dayOfMonth
                ),
                editProfissao.text.toString(),
                if (radioFeminino.isChecked)'F' else 'M'
            )

            //Salvar o registro
            //Em um SharedPreferences

            //A instrução abaixo irá criar um
            // arquivo sharedPreferences se não existir
            // Se existir ele será aberto para edição
            val dados = getSharedPreferences("usuario", Context.MODE_PRIVATE)

            //Vamos criar objeto que permitirá a
            //edição dos dados do arquivo SharedPreferences
            val editor = dados.edit()
            editor.putInt("id", usuario.id)
            editor.putString("nome", usuario.nome)
            editor.putString("email", usuario.email)
            editor.putString("senha", usuario.senha)
            editor.putInt("peso", usuario.peso)
            editor.putFloat("altura", usuario.altura.toFloat())
            editor.putString("dataNascimento", usuario.dataNascimento.toString())
            editor.putString("profissao", usuario.profissao)
            editor.putString("sexo", usuario.sexo.toString())
            editor.apply()

        }

        Toast.makeText(this, "Usuário cadastrado!", Toast.LENGTH_SHORT).show()

        return true
    }

    fun validarCampos(): Boolean {
        var valido = true
        if (editEmail.text.isEmpty()) {
            editEmail.error = "O e-mail é obrigatório!"
            valido = false
        }
        if (editSenha.text.isEmpty()) {
            editSenha.error = "A senha é obrigatória!"
            valido = false
        }
        if (editNome.text.isEmpty()) {
            editNome.error = "O nome é obrigatória!"
            valido = false
        }
        if (editProfissao.text.isEmpty()) {
            editProfissao.error = "A profisão é obrigatória!"
            valido = false
        }
        if (editAltura.text.isEmpty()) {
            editAltura.error = "A altura é obrigatória!"
            valido = false
        }
        if (etData.text.isEmpty()) {
            etData.error = "A data é obrigatória!"
            valido = false
        }
        return false
    }
}