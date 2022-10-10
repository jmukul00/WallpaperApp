package com.example.wallpaperapp.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wallpaperapp.R
import com.example.wallpaperapp.adapter.InterestAdapter
import com.example.wallpaperapp.adapter.InterestItemAdapter
import com.example.wallpaperapp.adapter.InterestItemSelectedListener
import com.example.wallpaperapp.databinding.ActivityMainBinding
import com.example.wallpaperapp.databinding.FragmentHomeBinding
import com.example.wallpaperapp.utils.SharedPrefs
import java.util.*


class HomeFragment : Fragment(), InterestItemSelectedListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var interestItemAdapter: InterestItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding =  FragmentHomeBinding.inflate(layoutInflater, container, false)


        val interestList = SharedPrefs(requireContext()).getSharedPrefs("interestList")


        interestItemAdapter = InterestItemAdapter(requireContext(),interestList, this)

        val layoutManager =
            LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        binding.rvInterestItem.layoutManager = layoutManager

        binding.rvInterestItem.adapter = interestItemAdapter



        return binding.root
    }

    override fun onInterestItemSelected(name: String) {
        Log.d("Home Fragment", "onInterestItemSelected: Clicked ")
    }


}