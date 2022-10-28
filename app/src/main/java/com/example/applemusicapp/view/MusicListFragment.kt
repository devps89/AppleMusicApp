package com.example.applemusicapp.view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.applemusicapp.databinding.MusicListFragmentBinding
import com.example.applemusicapp.remote.MusicResponse

private const val TAG = "MusicListFragment"

class MusicListFragment : Fragment() {
    private lateinit var binding: MusicListFragmentBinding
    private lateinit var rvMusicList: RecyclerView
    private lateinit var btnTestApi: Button
    private lateinit var bridge: Communicator


    override fun onAttach(context: Context) {
        super.onAttach(context)
        when (context) {
            is Communicator -> bridge = context
            else -> throw IllegalAccessException("Incorrect Host Activity")
        }
    }

    private val musicHandler =
        object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                Log.d(TAG, "handleMessage:")
                var listOfMusic: List<MusicResponse> = msg.obj as List<MusicResponse>
                Log.d(TAG, "handleMessage: $listOfMusic")

            }
        }

//    private val movieHandler =
//        object : Handler() {
//            override fun handleMessage(msg: Message) {
//                super.handleMessage(msg)
//                when (msg.what) {
//                    1 -> {
//                        val listOfMovies: List<MovieResponse> =
//                            msg.obj as List<MovieResponse>
//                        Log.d(TAG, "handleMessage: $listOfMovies")
//                        rvMovieList.adapter = MovieAdapter(listOfMovies)
//                    }
//                    else -> {
//                        msg.data?.getString("KEY")?.let {
//                            Toast.makeText(
//                                this@MainActivity,
//                                it, Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }
//
//
//                }
//            }
//        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: $container")
        super.onCreateView(inflater, container, savedInstanceState)

        binding = MusicListFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        initViews()
        // getMusicList()
//        val tvTest: TextView = binding.tvTest
//        tvTest.text = "hghgjhgghjghj"
        return binding.root
    }


    fun initViews() {
//        rvMusicList = binding.rvMusicList
        btnTestApi = binding.btnTestCallApi
        btnTestApi.setOnClickListener {
            bridge.doSearch(
                "rock",
                "music",
                "song",
                10
            )
        }

    }

//    private fun getMusicList() {
//        Log.d(TAG, "getMusicList: enter 1")
//        val network = MusicNetwork()
//        Thread(Runnable {
//            val msg = Message()
//            msg.obj = network.getMusicList()
//            musicHandler.sendMessage(msg)
//            network.getMusicList()
//        }).start()
//    }


//    private fun getMovieList() {
//        val network = MovieNetwork()
//        Thread(Runnable {
//            Log.d(TAG, "getMovieList1: ${Thread.currentThread().name}")
//            val message = Message()
//            message.what = 1
//            message.obj = network.getMovieList()
//            movieHandler.sendMessage(message)//here we are linking the handler with the thread, with the message
////            network.getMovieList()
//        }).start()
//
//
//        Thread(Runnable {
//            Log.d(TAG, "getMovieList2:  ${Thread.currentThread().name}")
//            movieHandler.sendMessage(
//                Message().apply {
//                    data = Bundle().apply {
//                        what = 2
//                        putString("KEY", "${Thread.currentThread().name}")
//                    }
//                }
//            )
//        }).start()
//
////        return ex.call()
//    }


}