package com.example.applemusicapp.view

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applemusicapp.R
import com.example.applemusicapp.databinding.MusicListFragmentBinding
import com.example.applemusicapp.remote.MusicItem
import com.example.applemusicapp.remote.MusicResponse
import com.example.applemusicapp.view.adapter.MusicAdapter


private const val TAG = "MusicListFragment"

class MusicListFragment : Fragment() {
    private lateinit var binding: MusicListFragmentBinding
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
    ): View {
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
        return binding.root
    }

    private fun initViews() {
        binding.rvMusicList.layoutManager = LinearLayoutManager(context)
    }

    private fun updateAdapter(dataSet: MusicResponse) {
        Log.d(TAG, "updateAdapter: ")

        //MediaPlayer instance created
        //it is created out side of the curly bracers
        //because if is  declared inside it  will be play many songs
        val mediaPlayer = MediaPlayer()
        binding.rvMusicList.adapter = MusicAdapter(parseListMusicInfo(dataSet)) {

            val previewAudio = it.previewUrl
            try {
                mediaPlayer.reset()//is necessary to use previous reuse the mediaPlayer
                mediaPlayer.setDataSource(previewAudio)
                mediaPlayer.prepare()
                mediaPlayer.start()

            } catch (ex: Exception) {
                Toast.makeText(context, getString(R.string.error_play_song), Toast.LENGTH_SHORT).show()
            }

        }
        Log.d(TAG, "updateAdapter: end")
    }

    private fun parseListMusicInfo(dataSet: MusicResponse): List<MusicItem> {
        return dataSet.results.map { item ->
            MusicItem(
                item.artistName,
                item.trackName,
                item.artworkUrl60,
                item.trackPrice,
                item.previewUrl
            )
        }

    }

}