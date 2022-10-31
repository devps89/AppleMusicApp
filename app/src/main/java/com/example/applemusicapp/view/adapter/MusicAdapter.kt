package com.example.applemusicapp.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemusicapp.databinding.MusicItemLayoutBinding
import com.example.applemusicapp.remote.MusicItem
import com.squareup.picasso3.Picasso


private const val TAG = "MusicAdapter"

class MusicAdapter(
    private val dataSet: List<MusicItem>,
    private val openDetails: (MusicItem) -> Unit
) : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {
    class MusicViewHolder(private val binding: MusicItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(musicItem: MusicItem, openDetails: (MusicItem) -> Unit) {
            Log.d(TAG, "bind: 1")
            binding.root.setOnClickListener {
                openDetails(musicItem)
            }
            binding.tvMusicName.text = musicItem.trackName
            binding.tvMusicArtist.text = musicItem.artistName
            binding.tvMusicPrice.text = musicItem.trackPrice

            //the images are hosted in API and it use "HTTPS" but the url is provided as "HTTP"
            //so i replace the http for https
            val imgUrl = musicItem.artworkUrl60.replace("http:", "https:")
            Picasso.Builder(binding.root.context)
                .build()
                .load(imgUrl).resize(200, 200)
                .into(binding.ivMusicImg)

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