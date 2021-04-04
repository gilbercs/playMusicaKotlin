package br.com.gilbercs.playmusic.adapter

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import br.com.gilbercs.playmusic.R
import br.com.gilbercs.playmusic.activity.MainActivity
import br.com.gilbercs.playmusic.model.ModelMusica

/**
 * Gilber C Souza
 */
class AdapterMusica(val lMusica: ArrayList<ModelMusica>, val context: Context):
    RecyclerView.Adapter<AdapterMusica.ViewHolder>(){
    //O uso do ViewHolder, em conjunto com os dois métodos, permite que views não
    // mais usadas possam ser reaproveitadas quando necessário.
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tNomeMusica = itemView.findViewById<TextView>(R.id.idAdpNomeMusica)
        val tNomeArtista = itemView.findViewById<TextView>(R.id.idAdpNomeArtista)
        fun itens(mMusica: ModelMusica){
            tNomeMusica.text = mMusica.nomeMusica
            tNomeArtista.text = mMusica.nomeArtista
        }

    }
//Metodo p/ inflar o layout do item. Basicamente é chamado quando é necessário criar um novo item.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listaLayout = LayoutInflater.from(parent.context).inflate(R.layout.adapter_musica,parent, false)
        return ViewHolder(listaLayout)
    }
    //O método: tem a finalidade de definir os atributos de exibição com base nos dados.
    // Basicamente é invocado quando um item precisa ser exibido para o usuário.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val id = holder.itens(lMusica[position])

    }
    //retorna a quantidade de itens no RecyclerView
    override fun getItemCount(): Int {
        return lMusica.size
    }
}