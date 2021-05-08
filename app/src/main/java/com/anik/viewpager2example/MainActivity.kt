package com.anik.viewpager2example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anik.viewpager2example.databinding.ActivityMainBinding
import com.anik.viewpager2example.slider.LoginGuideActivity
import com.anik.viewpager2example.withtablayout.TabLayoutExampleActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.withTabLayoutButton.setOnClickListener {
            startActivity(TabLayoutExampleActivity.newIntent(this))
        }

        binding.asSliderButton.setOnClickListener {
            startActivity(LoginGuideActivity.newIntent(this))
        }

    }

}