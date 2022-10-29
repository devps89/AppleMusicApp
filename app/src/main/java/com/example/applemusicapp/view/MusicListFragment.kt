package com.example.applemusicapp.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applemusicapp.databinding.MusicListFragmentBinding
import com.example.applemusicapp.remote.MusicItem
import com.example.applemusicapp.remote.MusicResponse
import com.example.applemusicapp.view.adapter.MusicAdapter

private const val TAG = "MusicListFragment"

class MusicListFragment : Fragment() {
    private lateinit var binding: MusicListFragmentBinding
    private lateinit var rvMusicList: RecyclerView
    private lateinit var btnTestApi: Button
    private lateinit var bridge: Communicator


    companion object {
        const val DISPLAY_MUSIC = "DISPLAY-MUSIC"
        fun newInstance(musicResponse: MusicResponse): MusicListFragment {
            val fragment = MusicListFragment()
            val bundle = Bundle()
            bundle.putParcelable(DISPLAY_MUSIC, musicResponse)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: attached")
        when (context) {
            is Communicator -> bridge = context
            else -> throw IllegalAccessException("Incorrect Host Activity")
        }
    }

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

        arguments?.getParcelable<MusicResponse>(DISPLAY_MUSIC)?.let {
            updateAdapter(it)
        }
        initViews()
        val kk=binding.rvMusicList.adapter
        Log.d(TAG, "onCreateView: $kk")
        //bridge.doSearch("rock", "music", "song", 10)
        //Log.d(TAG, "onCreateView: $kk")
        return binding.root
    }

    fun initViews() {
        binding.rvMusicList.layoutManager = LinearLayoutManager(context)
        // binding.rvBookResult.adapter = BookAdapter(emptyList(), ::navigateDetails)
//        btnTestApi = binding.btnTestCallApi
//        btnTestApi.setOnClickListener {
//            bridge.doSearch(
//                "rock",
//                "music",
//                "song",
//                10
//            )
//        }
    }

    private fun updateAdapter(dataSet: MusicResponse) {
        Log.d(TAG, "updateAdapter: ")
        binding.rvMusicList.adapter = MusicAdapter(parseListMusicInfo(dataSet)) {
            Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
        }
        Log.d(TAG, "updateAdapter: end")
    }

    private fun parseListMusicInfo(dataSet: MusicResponse): List<MusicItem> {
        return dataSet.results.map { item ->
            MusicItem(
                item.artistName,
                item.collectionName,
                item.artworkUrl60,
                item.trackPrice,
                )
        }

    }

}