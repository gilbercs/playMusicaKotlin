package br.com.gilbercs.playmusic.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import br.com.gilbercs.playmusic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(LayoutInflater.from(this))}
/*    private val bindingPlayer by lazy { RecyclerPlayerBinding.inflate(LayoutInflater.from(this))}

    var listaMusica = ArrayList<ModelMusica>()
    lateinit var recyLista: RecyclerView
    lateinit var seekBarMp: SeekBar

    val REQUEST_CODE = 123*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*inicializarCamponentes()
        checarPermissao()*/

        //carregarMusica()
        //iniciarSeebar()
    }

/*    fun inicializarCamponentes() {
        seekBarMp = bindingPlayer.idSeekBarMain  as SeekBar
        recyLista = binding.idRecyclearViewMusica  as RecyclerView
    }*/

    //chegar permisão ao dispositivo
    /*private fun checarPermissao() {
        if (Build.VERSION.SDK_INT >= 23) {
            if ((ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_CODE)
                //return
            } else {
                //chama carregar musica com recyclear view
                carregarMusica()
            }
        }
    }*/

    //verificar permissão
    /*override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        when (requestCode) {
            REQUEST_CODE -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                //carregarMusica()
            else
                Toast.makeText(this, "Permisão Negada", Toast.LENGTH_SHORT).show()
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }*/

    /*
    Cursor cursor = getContentResolver().query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                new String[] {MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM_ART},
                MediaStore.Audio.Albums._ID+ "=?",
                new String[] {String.valueOf(albumId)},
                null);

    if (cursor.moveToFirst()){
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));
        // do whatever you need to do
    }
    */





    /*fun carregarMusica() {
        val projection = arrayOf(
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.ALBUM
        )
        val selection = MediaStore.Audio.Media.IS_MUSIC + "!=0" //Determina o tipo .mp3
        val sortOrder = "${MediaStore.Audio.Media.DATE_ADDED} DESC"

        getApplication().contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            sortOrder
        )?.use { cursor ->
            while (cursor.moveToNext()) {
                listaMusica.add(
                    ModelMusica(
                        nomeMusica = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)),
                        nomeArtista = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)),
                        uri = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                    )
                )
            }
        }
        val adpMusica = AdapterMusica(listaMusica, this)
        recyLista.layoutManager = LinearLayoutManager(this)
        recyLista.adapter = adpMusica
    }*/













    /*var mediaPlayer: MediaPlayer? = null
    var imgPlay: ImageView? = null
    var imgPause: ImageView? = null
    var imgAnterior: ImageView? = null
    var imgProxima: ImageView? = null

    fun playMusica(uri: Uri) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, uri)
            //iniciarSeebar()
            mediaPlayer?.start()
            imgPlay!!.setImageResource(R.drawable.ic_pause_36)
        } else if (mediaPlayer != null) {
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer?.release()
            mediaPlayer = null
            mediaPlayer = MediaPlayer.create(this, uri)
            //iniciarSeebar()
            mediaPlayer?.start()
            imgPlay!!.setImageResource(R.drawable.ic_pause_36)
        }
        //play
        imgPlay!!.setOnClickListener {
            if (mediaPlayer!!.isPlaying) {
                mediaPlayer?.pause()
                imgPlay!!.setImageResource(R.drawable.ic_play_36)
            } else {
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(this, uri)
                    //iniciarSeebar()
                }
                imgPlay!!.setImageResource(R.drawable.ic_pause_36)
                mediaPlayer?.start()
            }
        }
        //stop
        imgPause!!.setOnClickListener {
            if (mediaPlayer != null) {
                mediaPlayer?.stop()
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer = null
                imgPlay!!.setImageResource(R.drawable.ic_play_36)
            }
        }
        //seeBar
*//*        seekBarMp.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mediaPlayer?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })*//*
    }*/




/*

    //função seekBarMain controlar a duração
    private fun iniciarSeebar() {
        seekBarMp.max = mediaPlayer!!.duration
        val executar = Handler()
        executar.postDelayed(object : Runnable {
            override fun run() {
                try {
                    seekBarMp.progress = mediaPlayer!!.currentPosition
                    executar.postDelayed(this, 1000)
                } catch (erro: Exception) {
                    seekBarMp.progress = 0
                }
            }
        }, 0)
    }

    //fun onBackPressed
    override fun onBackPressed() {
        if (mediaPlayer != null) {
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer?.release()
            mediaPlayer = null
        }
        finish()
        super.onBackPressed()
    }*/

}

