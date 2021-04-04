package br.com.gilbercs.playmusic.activity

import android.content.pm.PackageManager
import android.media.AudioManager
import android.media.MediaParser
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.gilbercs.playmusic.R
import br.com.gilbercs.playmusic.adapter.AdapterMusica
import br.com.gilbercs.playmusic.model.ModelMusica

class MainActivity : AppCompatActivity() {
    //declaração de variaveis
    var listaMusica = ArrayList<ModelMusica>()
    lateinit var recyLista: RecyclerView
    lateinit var seekBarMp: SeekBar
    var mP: MediaPlayer?=null
    var imgPlay: ImageView?=null
    var imgPause: ImageView?=null
    var imgAnterior: ImageView?=null
    var imgProxima: ImageView?=null
    val REQUEST_CODE = 123
    //inicializar componentes
    fun inicializarCamponentes(){
        seekBarMp = findViewById<SeekBar>(R.id.idSeekBarMain) as SeekBar
        recyLista = findViewById<RecyclerView>(R.id.idRecyclearViewMusica) as RecyclerView
    }
//metodo principal class
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializarCamponentes()
        checarPermissao()
        //carregarMusica()
    }
    //chegar permisão ao dispositivo
    private fun checarPermissao() {
        if (Build.VERSION.SDK_INT >= 23){
            if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE)
                return
            }
        }
        //chama carregar musica com recyclear view
        carregarMusica()
    }
    //verificar permissão
    override fun onRequestPermissionsResult(
        requestCode: Int,//variavel tipo inteiro
        permissions: Array<out String>, //array string
        grantResults: IntArray // int Array
    ) {
        when(requestCode){
            REQUEST_CODE -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //carregar lista do dispositivo
                carregarMusica()
            }else{
                Toast.makeText(this, "Permisão Negada",Toast.LENGTH_SHORT).show()
            }else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
    //metodo para carregar musica da base de dados do aparelho
    fun carregarMusica(){
        //declaração de variavel
        val ltMusica = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selectMusica = MediaStore.Audio.Media.IS_MUSIC+"!=0"
        val cursor = contentResolver.query(ltMusica,null,selectMusica,null,null)
        //realizar teste no cursor, verificar se é !=null ou esta em branco
        if (cursor != null){//inicia condicional I
            if (cursor!!.moveToFirst()){//inicia condicional II
                do {
                    val url = cursor!!.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                    val cmMusica = cursor!!.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                    val cmAutor = cursor!!.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    listaMusica.add(ModelMusica(cmMusica, cmAutor,url))
                }while (cursor!!.moveToNext())
              //
            }//fim condicional II
            cursor!!.close()
            val adpMusica = AdapterMusica(listaMusica, this)
            recyLista.layoutManager = LinearLayoutManager(this)
           recyLista.adapter = adpMusica
        }//fim condicional I
    }//fim carregar musica
//falta implementar ação de cliente no iten da list e executar
    //função play musica
    fun playMusica(uri: Uri) {
        if (mP==null){
            mP = MediaPlayer.create(this,uri)
            iniciarSeebar()
            mP?.start()
            imgPlay!!.setImageResource(R.drawable.ic_pause_36)
        }else if (mP != null){
            mP?.stop()
            mP?.reset()
            mP?.release()
            mP = null
            mP = MediaPlayer.create(this, uri)
            iniciarSeebar()
            mP?.start()
            imgPlay!!.setImageResource(R.drawable.ic_pause_36)
        }
        //play
        imgPlay!!.setOnClickListener {
            if (mP!!.isPlaying) {
                mP?.pause()
                imgPlay!!.setImageResource(R.drawable.ic_play_36)
            } else {
                if (mP == null) {
                    mP = MediaPlayer.create(this, uri)
                    iniciarSeebar()
                }
                imgPlay!!.setImageResource(R.drawable.ic_pause_36)
                mP?.start()
            }
        }
        //stop
        imgPause!!.setOnClickListener {
            if (mP != null){
                mP?.stop()
                mP?.reset()
                mP?.release()
                mP = null
                imgPlay!!.setImageResource(R.drawable.ic_play_36)
            }
        }
        //seeBar
        seekBarMp.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mP?.seekTo(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }//fim play musica
    //função seekBarMain controlar a duração
    private fun iniciarSeebar(){
        seekBarMp.max = mP!!.duration
        val executar = Handler()
        executar.postDelayed(object : Runnable{
            override fun run() {
                try {
                    seekBarMp.progress = mP!!.currentPosition
                    executar.postDelayed(this, 1000)
                }catch (erro: Exception){
                    seekBarMp.progress = 0
                }
            }
        },0)
    }
//fun onBackPressed
    override fun onBackPressed() {
        if (mP != null){
            mP?.stop()
            mP?.reset()
            mP?.release()
            mP=null
        }
        finish()
        super.onBackPressed()
    }

}

