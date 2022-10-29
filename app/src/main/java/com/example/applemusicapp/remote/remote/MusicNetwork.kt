package com.example.applemusicapp.remote.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val TAG = "MusicNetwork"

class MusicNetwork {

    companion object {
        const val BASE_URL = "https://itunes.apple.com/"
        const val END_POINT = "search"
//const val BASE_URL = "https://gist.githubusercontent.com/"
//        const val ENDPOINT =
//            "AntoninoAN/f3fa4b2260c51a5f80904c747009289e/raw/a1403b33a4c0d9a40d1901f6aeba065abc748a38/MovieGist"


        //lazy
        val musicApi: MusicAPI by lazy {
            initRetrofit().create(MusicAPI::class.java)
        }


        private fun initRetrofit(): Retrofit {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }

    }

}