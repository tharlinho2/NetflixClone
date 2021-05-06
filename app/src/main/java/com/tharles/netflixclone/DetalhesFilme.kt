package com.tharles.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.squareup.picasso.Picasso
import com.tharles.netflixclone.Adapter.FilmesAdapter
import com.tharles.netflixclone.Model.addFilmes
import com.tharles.netflixclone.databinding.ActivityDetalhesFilmeBinding

class DetalhesFilme : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesFilmeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesFilmeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        Toolbar()

        val recycler_outros_filmes = binding.recyclerOutrosFilmes
        recycler_outros_filmes.adapter = FilmesAdapter(addFilmes())
        recycler_outros_filmes.layoutManager = GridLayoutManager(applicationContext,3)

        val capaTheWitcher = "https://firebasestorage.googleapis.com/v0/b/netflix-clone-63c1d.appspot.com/o/video.jpg?alt=media&token=4e3c0ac1-51a9-4654-bce2-c98fa0ac2691"
        Picasso.get().load(capaTheWitcher).fit().into(binding.capa)

    }
    private fun Toolbar(){
        val toolbar_detalhe = binding.toolbarDetalhes
        toolbar_detalhe.setNavigationIcon(getDrawable(R.drawable.ic_voltar))
        toolbar_detalhe.setNavigationOnClickListener {
            val intent = Intent(this,ListaFilmes::class.java)
            startActivity(intent)
            finish()
        }
    }
}