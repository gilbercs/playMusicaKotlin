package br.com.gilbercs.playmusic.activity.mainFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import br.com.gilbercs.playmusic.databinding.RecyclerMusicaBinding
import br.com.gilbercs.playmusic.model.ModelMusica

class MainFragmentAdapter : RecyclerView.Adapter<MainFragmentAdapter.ViewHolderMusicas>() {

    var listMusica: MutableList<ModelMusica>? = null
    var context: Context? = null
    //var mediaPlayer: MediaPlayer? = null
    lateinit var viewModel:MainViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMusicas {
        val bindingRecyclerView = RecyclerMusicaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolderMusicas(bindingRecyclerView)
    }

    override fun onBindViewHolder(viewHolderMusicas: ViewHolderMusicas, position: Int){
        viewHolderMusicas.musica(listMusica!![position])

        viewHolderMusicas.cardMusic.setOnClickListener {
            viewModel.playMusic(listMusica!![position].uri.toUri())
            viewModel.hasMusicSelected()
            //viewModel.configSeekBar()
        }
    }

    override fun getItemCount() = listMusica!!.size

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
}