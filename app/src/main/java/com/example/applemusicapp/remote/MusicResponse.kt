package com.example.applemusicapp.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MusicResponse(
    val resultCount: Int,
    val results: List<MusicItem>
) : Parcelable

@Parcelize
data class MusicItem(
    val artistName: String = "",
    val collectionName: String = "",
    val artworkUrl60: String = "",
    val trackPrice: String? = "free",// there is one or some that doesn't has price attribute (no empty)
    val previewUrl: String? = "" // this parameter is not in the requirements but i added for play de song
) : Parcelable

