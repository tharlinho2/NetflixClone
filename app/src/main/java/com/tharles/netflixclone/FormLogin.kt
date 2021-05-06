package com.tharles.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.tharles.netflixclone.databinding.ActivityFormLoginBinding

class FormLogin : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        VerificarUsuarioLogado()

        binding.txtTelaCadastro.setOnClickListener {

            val intent = Intent(this,FormCadastro::class.java)
            startActivity(intent)
        }

        binding.btEntrar.setOnClickListener {

            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val mensagem_erro = binding.mensagemErro

            if (email.isEmpty() || senha.isEmpty()){
                mensagem_erro.setText("Preencha todos os campos!")

            }else {
                AutenticarUsuario()

            }
        }
    }
    private fun AutenticarUsuario(){
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val mensagem_erro = binding.mensagemErro

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(this,"Login efetuado com Sucesso!",Toast.LENGTH_SHORT).show()
                IrParaTelaFilmes()
            }
        }.addOnFailureListener {

            var erro = it

            when{
                erro is FirebaseAuthInvalidCredentialsException -> mensagem_erro.setText("E-mail ou Senha estão incorretos")
                erro is FirebaseNetworkException -> mensagem_erro.setText("Sem conexão de internet!")
                else -> mensagem_erro.setText("Erro ao logar usuário")
            }
        }
    }

    private fun VerificarUsuarioLogado(){
        val usuarioLogado = FirebaseAuth.getInstance().currentUser

        if (usuarioLogado != null){
            IrParaTelaFilmes()
        }
    }

    private fun IrParaTelaFilmes(){
        val intent = Intent(this,ListaFilmes::class.java)
        startActivity(intent)
        finish()
    }
}