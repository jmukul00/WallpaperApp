package com.example.wallpaperapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wallpaperapp.databinding.ActivityMainBinding
import com.example.wallpaperapp.ui.home.HomeFragment
import com.example.wallpaperapp.utils.SharedPrefs

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

      //  binding.txtInterest.text = SharedPrefs(this).getSharedPrefs("interestList")!![0].trim()

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameContainer, HomeFragment.newInstance())
            .commitNow()

        setContentView(binding.root)
        /*if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commitNow()
        }*/
    }
}