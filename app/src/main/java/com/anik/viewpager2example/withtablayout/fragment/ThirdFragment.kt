package com.anik.viewpager2example.withtablayout.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anik.viewpager2example.R

class ThirdFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = ThirdFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}