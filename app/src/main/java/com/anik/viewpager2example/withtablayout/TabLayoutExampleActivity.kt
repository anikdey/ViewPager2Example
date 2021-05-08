package com.anik.viewpager2example.withtablayout

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anik.viewpager2example.withtablayout.adapters.ExampleViewPagerAdapter
import com.anik.viewpager2example.databinding.ActivityTabLayoutExampleBinding
import com.anik.viewpager2example.withtablayout.fragment.FirstFragment
import com.anik.viewpager2example.withtablayout.fragment.SecondFragment
import com.anik.viewpager2example.withtablayout.fragment.ThirdFragment
import com.anik.viewpager2example.withtablayout.pagetransformer.DepthPageTransformer
import com.anik.viewpager2example.withtablayout.pagetransformer.ZoomOutPageTransformer
import com.google.android.material.tabs.TabLayoutMediator

class TabLayoutExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabLayoutExampleBinding
    private lateinit var pagerAdapter: ExampleViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabLayoutExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewPager()
    }

    private fun setUpViewPager() {
        pagerAdapter = ExampleViewPagerAdapter(this)
        pagerAdapter.addItem("First", FirstFragment.newInstance())
        pagerAdapter.addItem("Second", SecondFragment.newInstance())
        pagerAdapter.addItem("Third", ThirdFragment.newInstance())
        binding.viewPager.setPageTransformer(ZoomOutPageTransformer())
        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = pagerAdapter.getPageTitle(position)
        }.attach()
    }

    companion object {

        @JvmStatic
        fun newIntent(context: Context): Intent {
            return Intent(context, TabLayoutExampleActivity::class.java)
        }

    }

}