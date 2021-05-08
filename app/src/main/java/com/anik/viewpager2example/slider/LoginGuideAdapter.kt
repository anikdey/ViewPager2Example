package com.anik.viewpager2example.slider

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anik.viewpager2example.Constants
import com.anik.viewpager2example.databinding.ItemDemoBinding
import com.anik.viewpager2example.databinding.ItemTextImageBinding

class LoginGuideAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = ArrayList<SliderItem>()

    fun addItems(list: ArrayList<SliderItem>) {
        items = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == Constants.VIEW_TYPE_ONLY_TEXT) {
            var binding = ItemDemoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return DemoViewHolder(binding)
        } else {
            var binding = ItemTextImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return TextImageViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is DemoViewHolder) {
            var demoViewHolder = holder as DemoViewHolder
            demoViewHolder.setUpView(item = items[position])
        } else if(holder is TextImageViewHolder) {
            var textImageViewHolder = holder as TextImageViewHolder
            textImageViewHolder.setUpView(item = items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }

    class DemoViewHolder(private val binding: ItemDemoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setUpView(item: SliderItem) {
            binding.instructionTextView.text = item.instruction
        }

    }

    class TextImageViewHolder(private val binding: ItemTextImageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setUpView(item: SliderItem) {
            binding.instructionTextView.text = item.instruction
            item.resId?.let {
                binding.imageView.setImageResource(it)
            }
        }
    }

}