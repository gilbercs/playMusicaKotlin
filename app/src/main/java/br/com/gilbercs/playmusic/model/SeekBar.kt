/*
package br.com.gilbercs.playmusic.model

import android.media.MediaPlayer
import android.os.Handler
import android.widget.SeekBar

class SeekBar {
companion object
    {    lateinit var seekBarMp: SeekBar
        var mediaPlayer: MediaPlayer? = null

        fun iniciar() {
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
    }
}*/
