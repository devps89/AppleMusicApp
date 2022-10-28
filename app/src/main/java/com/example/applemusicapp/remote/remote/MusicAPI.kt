package com.example.applemusicapp.remote.remote


import com.example.applemusicapp.remote.MusicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicAPI {

    @GET(MusicNetwork.END_POINT)
    fun getMusicByFilters(
        @Query("term") musicName: String = "",
        @Query("amp;media") entity: String = "",
        @Query("amp;entity") media: String = "",
        @Query("amp;limit") limit: Int = 1,

        ): Call<MusicResponse>

//    @GET(BookNetwork.END_POINT)
//    fun getBooksByFilters(
//        @Query("q") bookTitle: String,
//        @Query("filter") bookFilter: String,
//        @Query("maxResults") bookMaxResults: Int,
//        @Query("printType") bookPrintType: String
//    ): Call<BooksResponse>

}