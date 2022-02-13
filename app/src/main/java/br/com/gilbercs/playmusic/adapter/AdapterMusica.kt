package br.com.gilbercs.playmusic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import br.com.gilbercs.playmusic.databinding.RecyclerMusicaBinding
import br.com.gilbercs.playmusic.model.ModelMusica
import br.com.gilbercs.playmusic.model.PlayMusic

class AdapterMusica(val listMusica: ArrayList<ModelMusica>, val context: Context) :
    RecyclerView.Adapter<AdapterMusica.ViewHolderMusicas>() {

    class ViewHolderMusicas(itemView: RecyclerMusicaBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val nomeMusica = itemView.idAdpNomeMusica
        val nomeArtista = itemView.idAdpNomeArtista
        val cardMusic = itemView.cardMusic

        fun musica(musica: ModelMusica) {
            nomeMusica.text = musica.nomeMusica
            nomeArtista.text = musica.nomeArtista
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMusicas {
        val binding = RecyclerMusicaBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolderMusicas(binding)
    }

    override fun onBindViewHolder(viewHolderMusicas: ViewHolderMusicas, position: Int) {
        val id = viewHolderMusicas.musica(listMusica[position])

        viewHolderMusicas.cardMusic.setOnClickListener {
            PlayMusic(context).play(listMusica[position].uri.toUri())
        }

    }

    override fun getItemCount() = listMusica.size

}