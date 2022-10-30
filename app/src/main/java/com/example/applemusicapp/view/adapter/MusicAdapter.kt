package com.example.applemusicapp.view.adapter

import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemusicapp.databinding.MusicItemLayoutBinding
import com.example.applemusicapp.remote.MusicItem
import com.squareup.picasso3.Picasso


private const val TAG = "MusicAdapter"
var mMediaPlayer: MediaPlayer? = null

class MusicAdapter(
    val dataSet: List<MusicItem>,
    val openDetails: (MusicItem) -> Unit
) : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {
    class MusicViewHolder(private val binding: MusicItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(musicItem: MusicItem, openDetails: (MusicItem) -> Unit) {
            Log.d(TAG, "bind: 1")
            binding.root.setOnClickListener {
                openDetails(musicItem)
            }
            val musicName = musicItem.collectionName
            binding.tvMusicName.text = musicItem.collectionName
            binding.tvMusicArtist.text = musicItem.artistName
            binding.tvMusicPrice.text = musicItem.trackPrice

            var imgurl = musicItem.artworkUrl60.replace("http:", "https:")
            //Log.d(TAG, "bind: $imgurl")
            Picasso.Builder(binding.root.context)
                .build()
                .load(imgurl).resize(200, 200)
                .into(binding.ivMusicImg)

            //Log.d(TAG, "bind: $musicName")
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        Log.d(TAG, "onCreateViewHolder: ")
        return MusicViewHolder(
            MusicItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(dataSet[position], openDetails)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}