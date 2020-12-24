package com.example.gsport24.ui.screens.main.fragments.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.gsport24.databinding.AdapterNewsItemBinding
import com.example.gsport24.domain.entities.NewsDomain
import com.example.gsport24.mvvm.ui.BasePagingAdapter
import com.example.gsport24.mvvm.ui.BaseViewHolder
import com.example.gsport24.root.ext.convertFrom
import com.example.gsport24.root.ext.convertTo
import com.example.gsport24.root.utils.DatePatterns
import com.example.gsport24.root.utils.getDiffCallback
import com.bumptech.glide.Glide

class NewsListAdapter(private val onNewsClick : (NewsDomain) -> Unit) : BasePagingAdapter<NewsDomain, AdapterNewsItemBinding>(getDiffCallback()) {

	override fun inflate(inflater : LayoutInflater, parent : ViewGroup?, attachToParent : Boolean) : AdapterNewsItemBinding {
		return AdapterNewsItemBinding.inflate(inflater, parent, attachToParent)
	}

	override fun BaseViewHolder<AdapterNewsItemBinding, NewsDomain>.bindActionTo(data : NewsDomain) {
		binding.apply {
			Glide.with(holderContext)
				.load(data.photo)
				.into(photo)
			title.text = data.title
			date.text = data.date.convertFrom { DatePatterns.UTC }.convertTo { DatePatterns.DAY_MONTH_YEAR }
			root.setOnClickListener { onNewsClick(data) }
		}
	}

}