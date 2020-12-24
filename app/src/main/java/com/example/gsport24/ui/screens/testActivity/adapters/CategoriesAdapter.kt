package com.example.gsport24.ui.screens.testActivity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.gsport24.databinding.AdapterCategoryItemBinding
import com.example.gsport24.domain.entities.NotificationDomain
import com.example.gsport24.mvvm.ui.BaseListAdapter
import com.example.gsport24.mvvm.ui.BaseViewHolder
import com.example.gsport24.root.utils.getDiffCallback

class CategoriesAdapter(private val onItemClick:(Int)->Unit) : BaseListAdapter<NotificationDomain, AdapterCategoryItemBinding>(getDiffCallback()) {

    override fun inflate(inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean): AdapterCategoryItemBinding {
        return AdapterCategoryItemBinding.inflate(inflater,parent,attachToParent)
    }

    override fun BaseViewHolder<AdapterCategoryItemBinding, NotificationDomain>.bindActionTo(data: NotificationDomain) {
        binding.apply {
            tvCategory.text = data.description
            tvTime.text = data.id.toString()
            image.setOnClickListener { onItemClick(bindingAdapterPosition) }
            Glide.with(holderContext)
                .load("https://www.talkwalker.com/images/2020/blog-headers/image-analysis.png")
                .into(image)
        }
    }

}
