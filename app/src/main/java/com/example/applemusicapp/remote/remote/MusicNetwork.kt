package com.example.applemusicapp.remote.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MusicNetwork {

    companion object {
       private const val BASE_URL = "https://itunes.apple.com/"
        const val END_POINT = "search"

        val musicApi: MusicAPI by lazy {
            initRetrofit().create(MusicAPI::class.java)
        }


        private fun initRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

}