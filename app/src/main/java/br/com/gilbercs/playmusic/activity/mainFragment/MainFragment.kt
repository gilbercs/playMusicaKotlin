package br.com.gilbercs.playmusic.activity.mainFragment

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.gilbercs.playmusic.databinding.MainFragmentBinding
import br.com.gilbercs.playmusic.model.ModelMusica


class MainFragment : Fragment() {
    //Seekbar
    private var seekProgressMusic: SeekBar? = null
    //View
    private val binding by lazy { MainFragmentBinding.inflate(LayoutInflater.from(context)) }
    //private val bindingPlayer by lazy { RecyclerPlayerBinding.inflate(LayoutInflater.from(context)) }
    private lateinit var listaMusica: MutableList<ModelMusica>
    private lateinit var BarraMediaPlayer: SeekBar
    //Adapter & RecyclerView
    private val mainFragmentAdapter = MainFragmentAdapter()
    lateinit var recyclerView: RecyclerView
    //ViewModel
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configuraAudioManager()
        binding.btPlay.setOnClickListener {
            viewModel.buttonPlay()
        }

        binding.btPause.setOnClickListener {
            viewModel.buttonPause()
        }

        binding.btAdvance.setOnClickListener {
            Log.e("teste","Advance clicado")
        }

        binding.btBack.setOnClickListener {
            Log.e("teste","Back clicado")
        }

        configuraViewModel()
        inicializarCamponentes()

        if (checarPermissao()) {
            observers()
            viewModel.initContext(requireContext())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    private fun configuraViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun inicializarCamponentes() {
        recyclerView = binding.idRecyclearViewMusica
    }

    private fun observers() {
        viewModel.listaMusica.observe(viewLifecycleOwner, Observer { list ->
            listaMusica = list
            adapter(listaMusica)
        })

        viewModel.context.observe(viewLifecycleOwner, Observer {context ->
            Log.e("teste", "chama contexto de novo")
        })

        viewModel.hasMusic.observe(viewLifecycleOwner, Observer { hasMusic ->
            if(hasMusic) binding.idLinearlayoutPlayer.visibility = View.VISIBLE
        })

    }

    private fun adapter(list: MutableList<ModelMusica>) {
        mainFragmentAdapter.listMusica = list
        mainFragmentAdapter.context = context
        mainFragmentAdapter.viewModel = viewModel
        viewModel.seekProgressMusic = binding.seekBarMediaPlayer
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = mainFragmentAdapter
    }

    private fun checarPermissao(): Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            if ((ActivityCompat.checkSelfPermission(requireContext(),
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 123)
                if ((ActivityCompat.checkSelfPermission(requireContext(),
                        android.Manifest.permission.READ_EXTERNAL_STORAGE)) != PackageManager.PERMISSION_GRANTED
                ) {
                    return checarPermissao()
                }
            } else {
                return true
            }
        }
        return false
    }


    private fun configuraAudioManager() {
        seekProgressMusic = binding.seekBarMediaPlayer

        seekProgressMusic!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                //text_view.text = "Progress : $i"
                Log.e("teste","$progress")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                Toast.makeText(requireContext(),"-> start tracking",Toast.LENGTH_SHORT).show()

            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                Toast.makeText(requireContext(),"-> stop tracking",Toast.LENGTH_SHORT).show()

            }
        })
    }

}