package com.example.applemusicapp.remote

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class MusicResponse(
    val resultCount:Int,
    val results: List<MusicItem>
)


data class MusicItem(
    val artistName: String,
    val collectionName: String,
    val artworkUrl60: String,
    val trackPrice: String
)

