package com.vantula.unsplashtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vantula.unsplashtest.databinding.ActivityMainBinding
import com.vantula.unsplashtest.view.topics.TopicsFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TopicsFragment.newInstance()).commit()
        }
    }
}