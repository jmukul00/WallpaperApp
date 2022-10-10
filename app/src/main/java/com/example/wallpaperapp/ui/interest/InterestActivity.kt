package com.example.wallpaperapp.ui.interest


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wallpaperapp.MainActivity
import com.example.wallpaperapp.R
import com.example.wallpaperapp.adapter.InterestAdapter
import com.example.wallpaperapp.adapter.InterestSelectedListener
import com.example.wallpaperapp.databinding.ActivityInterestBinding
import com.example.wallpaperapp.utils.SharedPrefs

import kotlin.math.ceil
import kotlin.math.roundToInt

class InterestActivity : AppCompatActivity(), InterestSelectedListener {
    private val TAG = "InterestActivity"
    lateinit var interestAdapter: InterestAdapter
    private lateinit var interestViewModel: InterestViewModel
    lateinit var interestBinding: ActivityInterestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        interestBinding = ActivityInterestBinding.inflate(layoutInflater)
        Log.d(TAG, "onCreate:$interestBinding")
        interestViewModel = ViewModelProvider(this)[InterestViewModel::class.java]


        interestViewModel.getInterestMutableLiveData().observe(this) {

            Log.d(TAG, "onCreate: $it")
            interestAdapter = InterestAdapter(applicationContext, it, this)
            val spanCount: Int = if (it.size <= 5) {
                2
            } else {
                ceil((it.size.toDouble() / 3.0)).roundToInt()
            }
            val layoutManager =
                StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.HORIZONTAL)
            interestBinding.rvInterest.layoutManager = layoutManager
            interestBinding.rvInterest.adapter = interestAdapter


        }


        interestBinding.btnExplore.setOnClickListener {
            SharedPrefs(this).saveSharedPrefs("interestList", interestAdapter.getSelected())
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        setContentView(interestBinding.root)
    }

    override fun onInterestSelected() {
        if (interestAdapter.getSelected().size in 3..6) {
            interestBinding.btnExplore.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.primary
                )
            )
            interestBinding.btnExplore.isClickable = true
        } else {
            interestBinding.btnExplore.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.gray
                )
            )
            interestBinding.btnExplore.isClickable = false
        }

    }

}
