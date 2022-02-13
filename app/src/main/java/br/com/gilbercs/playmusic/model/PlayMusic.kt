package br.com.gilbercs.playmusic.model

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.widget.ImageView

class PlayMusic(val context: Context) {
    var mediaPlayer: MediaPlayer? = null
    var imgPlay: ImageView? = null
    var imgPause: ImageView? = null
    var imgAnterior: ImageView? = null
    var imgProxima: ImageView? = null

    fun play(uri: Uri) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, uri)
            //SeekBar.iniciar()
            mediaPlayer?.start()
        }else if (mediaPlayer != null) {
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer?.release()
            mediaPlayer = null
            mediaPlayer = MediaPlayer.create(context, uri)
            //iniciarSeebar()
            mediaPlayer?.start()
            //imgPlay!!.setImageResource(R.drawable.ic_pause_36)
        }
    }

    fun pause() {
        mediaPlayer?.pause()
    }

    /*//TODO: falta implementar ação de cliente no iten da list e executar
    fun playMusica(uri: Uri) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, uri)
            //SeekBar.iniciar()
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
        //seekBarMp.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mediaPlayer?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }*/
}
