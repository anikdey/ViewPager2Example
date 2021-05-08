package com.anik.viewpager2example.withtablayout.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ExampleViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    var fragmentList = ArrayList<Fragment>()
    var titleList = ArrayList<String>()

    fun getPageTitle(position: Int): String {
        return titleList[position]
    }

    fun addItem(title: String, fragment: Fragment) {
        fragmentList.add(fragment)
        titleList.add(title)
    }

    override fun getItemCount(): Int {
        return fragmentList.count()
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}