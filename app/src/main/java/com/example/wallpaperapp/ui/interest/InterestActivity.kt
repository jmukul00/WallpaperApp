package com.example.wallpaperapp.ui.interest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wallpaperapp.R
import com.example.wallpaperapp.adapter.InterestAdapter
import com.example.wallpaperapp.databinding.ActivityInterestBinding
import com.example.wallpaperapp.models.InterestModel
import com.example.wallpaperapp.ui.ui.home.HomeViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.math.ceil
import kotlin.math.roundToInt

class InterestActivity : AppCompatActivity() {
    private val TAG = "InterestActivity"
    val interestList: ArrayList<InterestModel> = ArrayList()
    lateinit var interestAdapter: InterestAdapter
    private lateinit var interestViewModel: InterestViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityInterestBinding.inflate(layoutInflater)
        interestViewModel = ViewModelProvider(this)[InterestViewModel::class.java]



        interestViewModel.getInterestMutableLiveData().observe(this) {

            Log.d(TAG, "onCreate: $it")
            interestAdapter = InterestAdapter(applicationContext, it)
            var spanCount = 0;
            spanCount = if (it.size<=5) {
                2
            } else {
                ceil((it.size.toDouble()/3.0)).roundToInt()
            }
            val layoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.HORIZONTAL)
            binding.rvInterest.layoutManager = layoutManager
            binding.rvInterest.adapter = interestAdapter

        }

        binding.btnExplore.setOnClickListener {
           if(interestAdapter.getSelected().size in 3..6){

           }
            else{
                Toast.makeText(applicationContext, "Please select interests between 3-6", Toast.LENGTH_SHORT).show()
           }
        }

        setContentView(binding.root)
    }
}