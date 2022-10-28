package com.example.applemusicapp.remote.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val TAG = "MusicNetwork"

class MusicNetwork {

    companion object {
        const val BASE_URL = "https://itunes.apple.com/"
        const val END_POINT =
            "search"
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


//
//        //        val query = java.lang.String.format(
////            "term=%s",
////            URLEncoder.encode("rock", "UTF-8")
////        )
//        val uriPath = Uri.parse("$BASE_URL$END_POINT?")
//        val url = URL(uriPath.toString())
//    }
//
//    fun getMusicList(): List<MusicResponse> {
//        Log.d(TAG, "getMusicList: enter in network $url")
//
//        val httpURLConnection = url.openConnection() as HttpURLConnection
//        Log.d(TAG, "getMusicList: 1")
//        httpURLConnection.readTimeout = 1000
//        httpURLConnection.connectTimeout = 15000
//        httpURLConnection.requestMethod = "GET"
//        httpURLConnection.doInput = true
//        Log.d(TAG, "getMusicList: 2")
//        httpURLConnection.connect()
//        Log.d(TAG, "getMusicList: 3")
//        return httpURLConnection.inputStream.run {
//            deserialize(this)
//        }.let {
//            parseToMusicResponse(it)
//        }
//
//    }
//
//
//    fun deserialize(iS: InputStream): String {
//        Log.d(TAG, "deserialize: $iS")
//        val builder = StringBuilder()
//        val reader = BufferedReader(InputStreamReader(iS))
//        var line: String? = reader.readLine()
//        Log.d(TAG, "deserialize: ${line.toString()}")
//        while (line != null) {
//            Log.d(TAG, "deserialize: ${line.toString()}")
//            builder.append(line.toString())
//            builder.append("\n")
//            line = reader.readLine()
//            Log.d(TAG, "deserialize: read another")
//        }
//        Log.d(TAG, "deserialize: finish while")
//
//        if (builder.isEmpty()) {
//            Log.d(TAG, "deserialize: isempty")
//            return "N/A"
//        }
//        //Log.d(TAG, "deserialize: ${builder.toString()}")
//        return builder.toString()
//
//    }
//
//    fun parseToMusicResponse(deserialize: String): List<MusicResponse> {
//        val resp = JSONObject(deserialize)
//        //Log.d(TAG, "parseToMusicResponse: 1 $resp")
//        val results = resp["results"]
//        println(results)
//        //Log.d(TAG, "parseToMusicResponse: $results")
//        val response = JSONArray(results)
//        Log.d(TAG, "parseToMusicResponse: parsed")
//        Log.d(TAG, "parseToMusicResponse: $response")
//        val listOfMusic = mutableListOf<MusicResponse>()
//        for (index in 0 until response.length()) {
//            val MusicItem = response.getJSONObject(index)
//            val musicResponse = MusicResponse(
//                artistName = MusicItem.getString("artistName"),
//                collectionName = MusicItem.getString("collectionName"),
//                artworkUrl60 = MusicItem.getString("artworkUrl60"),
//                trackPrice = MusicItem.getString("trackPrice")
//            )
//            listOfMusic.add(musicResponse)
//        }
//        return listOfMusic
    }

}