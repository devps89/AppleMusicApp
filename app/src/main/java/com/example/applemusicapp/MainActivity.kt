package com.example.applemusicapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.applemusicapp.remote.MusicResponse
import com.example.applemusicapp.remote.remote.MusicNetwork
import com.example.applemusicapp.view.Communicator
import com.example.applemusicapp.view.MusicListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity(), Communicator {

    private lateinit var navBottom: BottomNavigationView
    private lateinit var swpRefreshList: SwipeRefreshLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nb_bottom)
        bottomNavigationView.setupWithNavController(navController)

        doSearch("rock", "music", "song", 10)
        initViews()
    }

    private fun initViews() {
        swpRefreshList = findViewById(R.id.swp_refresh_list)
        navBottom = findViewById(R.id.nb_bottom)
        //add the listener for validate the item selected, and it will be fill with corresponded list
        val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.rock_fragment -> {
                        Log.d(TAG, "initViews: home")
                        doSearch("rock", "music", "song", 10)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.classics_fragment -> {
                        Log.d(TAG, "initViews: profile")
                        doSearch("classick", "music", "song", 10)

                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.pop_fragment -> {
                        Log.d(TAG, "initViews: settings")
                        doSearch("pop", "music", "song", 10)

                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }
        navBottom.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        //set a listener for refresh the list when pull down and call the fun "refreshingData"
        swpRefreshList.setOnRefreshListener {
            Toast.makeText(this, getString(R.string.msg_refreshing_list), Toast.LENGTH_SHORT).show()
            refreshingData()
            swpRefreshList.isRefreshing = false
        }
    }

    /**
     * Refresh the list after pull down swipe
     */
   private fun refreshingData() {
        when (navBottom.selectedItemId) {
            R.id.rock_fragment -> {
                Log.d(TAG, "initViews: home")
                doSearch("rock", "music", "song", 10)
            }
            R.id.classics_fragment -> {
                Log.d(TAG, "initViews: profile")
                doSearch("classick", "music", "song", 10)
            }
            R.id.pop_fragment -> {
                Log.d(TAG, "initViews: settings")
                doSearch("pop", "music", "song", 10)
            }
        }
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
                        Log.d(TAG, "onResponse: successfully")
                        val body = response.body()
                        createDisplayFragment(body)
                    } else {
                        Log.d(TAG, "onResponse:  error $response")
                    }
                }


                override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: $t")
                }
            }
        )

    }

    private fun createDisplayFragment(body: MusicResponse?) {
        Log.d(TAG, "createDisplayFragment: enter")
        body?.let {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.display_list_container,
                    MusicListFragment.newInstance(it)

                ).commit()
        }

    }

}
