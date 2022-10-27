package com.example.applemusicapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.applemusicapp.databinding.MusicListFragmentBinding

class MusicListFragment : Fragment() {
//    private lateinit var binding: MusicListFragmentBinding
//    private lateinit var msg: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = super.onCreateView(inflater, container, savedInstanceState)
        //val tvTest: TextView = binding.tvTest
        return root
    }
}