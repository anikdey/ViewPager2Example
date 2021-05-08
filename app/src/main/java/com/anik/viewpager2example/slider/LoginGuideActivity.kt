package com.anik.viewpager2example.slider

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.anik.viewpager2example.Constants
import com.anik.viewpager2example.R
import com.anik.viewpager2example.customview.DottedCircle
import com.anik.viewpager2example.databinding.ActivityLoginGuideBinding
import com.anik.viewpager2example.withtablayout.pagetransformer.DepthPageTransformer

class LoginGuideActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginGuideBinding
    private lateinit var loginGuideAdapter: LoginGuideAdapter

    private var onPageChangeCallbackListener = object : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            if(position == 0) {
                controlButtonVisibility(View.GONE, View.VISIBLE, View.GONE)
            } else if(position>0 && position<loginGuideAdapter.itemCount-1) {
                controlButtonVisibility(View.VISIBLE, View.VISIBLE, View.GONE)
            } else if(position == loginGuideAdapter.itemCount-1) {
                controlButtonVisibility(View.VISIBLE, View.GONE, View.VISIBLE)
            }
            addIndicators(binding.viewPager.currentItem)
        }

//        override fun onPageScrollStateChanged(state: Int) {
//        }
//
//        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginGuideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.doneButton.setOnClickListener {
            showToast("Implement as per your need..")
        }

        binding.prevButton.setOnClickListener {
            showPreviousItem()
        }

        binding.nextButton.setOnClickListener {
            showNextItem()
        }

        setUpViewPager()
        //addIndicators(0)
        //setUpUsingCustomView(0)
    }

    private fun showPreviousItem() {
        var currentPosition = binding.viewPager.currentItem
        if(currentPosition>0) {
            binding.viewPager.currentItem = currentPosition-1
        }
    }

    private fun showNextItem() {
        var currentPosition = binding.viewPager.currentItem
        if(currentPosition<loginGuideAdapter.itemCount) {
            binding.viewPager.currentItem = currentPosition+1
        }
    }

    private fun setUpViewPager() {
        loginGuideAdapter = LoginGuideAdapter()
        loginGuideAdapter.addItems(getItems())
        binding.viewPager.adapter = loginGuideAdapter
        binding.viewPager.setPageTransformer(DepthPageTransformer())
        binding.viewPager.registerOnPageChangeCallback(onPageChangeCallbackListener)
        //binding.viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        // Fix for the first time showing the dots....
        binding.viewPager.post {
            onPageChangeCallbackListener.onPageSelected(0)
        }
    }

    private fun addIndicators(position: Int) {
        binding.indicatorsContainer.removeAllViews()
        setUpUsingCustomView(position)
        //setUpUsingTextView(position)
    }

    private fun setUpUsingCustomView(position: Int) {
        binding.indicatorsContainer.removeAllViews()
        for (i in 0 until loginGuideAdapter.itemCount) {
            var layout = layoutInflater.inflate(R.layout.circular_dot_using_custom_view, null) as LinearLayout
            var dottedCircle = layout.findViewById<DottedCircle>(R.id.circle)
            layout.removeView(dottedCircle)
            if(position == i) {
                dottedCircle.setOuterCircleColor(ContextCompat.getColor(this, R.color.red))
                dottedCircle.setInnerCircleColor(ContextCompat.getColor(this, R.color.purple_500))
            }
            binding.indicatorsContainer.addView(dottedCircle)
        }
    }

    private fun setUpUsingTextView(position: Int) {
        for (i in 0 until loginGuideAdapter.itemCount) {
            var textView = layoutInflater.inflate(R.layout.circular_dot, null) as AppCompatTextView
            //textView.text = getText()
            if(position == i) {
                textView.setTextColor(ContextCompat.getColor(this, R.color.red))
            } else {
                textView.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
            binding.indicatorsContainer.addView(textView)
        }
    }

    private fun getText(): Spanned? {
        var html = "&#8226;"
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            Html.fromHtml(html);
        }
    }

    private fun controlButtonVisibility(prevVisibility: Int, nextVisibility: Int, doneVisibility: Int) {
        binding.prevButton.visibility = prevVisibility
        binding.nextButton.visibility = nextVisibility
        binding.doneButton.visibility = doneVisibility
    }

    private fun getItems(): ArrayList<SliderItem> {
        var list = ArrayList<SliderItem>()
        list.add(
            SliderItem(
                Constants.VIEW_TYPE_ONLY_TEXT,
                "Your first instruction goes here...",
                null
            )
        )
        list.add(
            SliderItem(
                Constants.VIEW_TYPE_TEXT_IMAGE,
                "Your second instruction goes here...",
                R.drawable.man_circle
            )
        )
        list.add(
                SliderItem(
                        Constants.VIEW_TYPE_TEXT_IMAGE,
                        "Your fourth instruction goes here...",
                        R.drawable.child
                )
        )
        list.add(
            SliderItem(
                Constants.VIEW_TYPE_ONLY_TEXT,
                "Your third instruction goes here...",
                null
            )
        )
        list.add(
            SliderItem(
                Constants.VIEW_TYPE_TEXT_IMAGE,
                "Your fourth instruction goes here...",
                R.drawable.child
            )
        )
        return list
    }

    private fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewPager.unregisterOnPageChangeCallback(onPageChangeCallbackListener)
    }

    companion object {

        @JvmStatic
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginGuideActivity::class.java)
        }

    }
}