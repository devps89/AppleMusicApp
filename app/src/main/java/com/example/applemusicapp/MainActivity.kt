package com.example.applemusicapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.applemusicapp.remote.MusicResponse
import com.example.applemusicapp.view.Communicator
import com.example.applemusicapp.remote.remote.MusicNetwork
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity(),Communicator {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nb_bottom)
        bottomNavigationView.setupWithNavController(navController)

    }

    override fun doSearch(term: String, media: String, entity: String, limit: Int) {
        Log.d(TAG, "doSearch: $term, $media, $entity, $limit")
        MusicNetwork.musicApi.getMusicByFilters(
            term,
            media,
            entity,
            limit
        ).enqueue(
            object : Callback<MusicResponse> {
                override fun onResponse(
                    call: Call<MusicResponse>,
                    response: Response<MusicResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.d(TAG, "onResponse: succesfuly")
                        val body = response.body()
                        Log.d(TAG, "onResponse: $response")
                        Log.d(TAG, "onResponse: $body")
                    } else {
                        Log.d(TAG, "onResponse:  error $response")
                    }
                }

                override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: ")
                }
            }
        )
    }

}
