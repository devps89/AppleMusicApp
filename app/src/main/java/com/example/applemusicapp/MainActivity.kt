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
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity(), Communicator {

    //lateinit var mainBinding: ActivityMainBinding
    lateinit var navBottom: BottomNavigationView
    private lateinit var navRock: BottomNavigationMenuView
    private lateinit var navClassic: BottomNavigationMenuView
    private lateinit var navPop: BottomNavigationMenuView
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

    fun initViews() {
        swpRefreshList = findViewById(R.id.swp_refresh_list)
        navBottom = findViewById(R.id.nb_bottom)
        val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.homeFragment -> {
                        Log.d(TAG, "initViews: home")
                        doSearch("rock", "music", "song", 10)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.profileFragment -> {
                        // put your code here
                        Log.d(TAG, "initViews: profile")
                        doSearch("classick", "music", "song", 10)

                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.settingsFragment -> {
                        // put your code here
                        Log.d(TAG, "initViews: settings")
                        doSearch("pop", "music", "song", 10)

                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }
        navBottom.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        swpRefreshList.setOnRefreshListener() {
            Toast.makeText(this, "refreshing", Toast.LENGTH_SHORT).show()
            refreshinngData()
            swpRefreshList.isRefreshing = false
        }
    }

    fun refreshinngData() {
        when (navBottom.selectedItemId) {
            R.id.homeFragment -> {
                Log.d(TAG, "initViews: home")
                doSearch("rock", "music", "song", 10)
            }
            R.id.profileFragment -> {
                // put your code here
                Log.d(TAG, "initViews: profile")
                doSearch("classick", "music", "song", 10)
            }
            R.id.settingsFragment -> {
                // put your code here
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
                        Log.d(TAG, "onResponse: succesfuly")
                        val body = response.body()
                        //Log.d(TAG, "onResponse: $response")
                        //Log.d(TAG, "onResponse: $body")
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
