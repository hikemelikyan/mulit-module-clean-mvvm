package com.example.gsport24.ui.screens.main.fragments.categories.categoriesList.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.gsport24.databinding.AdapterCategoryItemHorizontalBinding
import com.example.gsport24.domain.entities.CategoryDomain
import com.example.gsport24.mvvm.ui.BaseListAdapter
import com.example.gsport24.mvvm.ui.BaseViewHolder
import com.example.gsport24.root.ext.convertFrom
import com.example.gsport24.root.ext.convertTo
import com.example.gsport24.root.utils.DatePatterns
import com.example.gsport24.root.utils.getDiffCallback
import com.bumptech.glide.Glide

class CategoriesAdapter(private val onItemClick : (Int) -> Unit) : BaseListAdapter<CategoryDomain, AdapterCategoryItemHorizontalBinding>(getDiffCallback()) {

	override fun inflate(inflater : LayoutInflater, parent : ViewGroup?, attachToParent : Boolean) : AdapterCategoryItemHorizontalBinding {
		return AdapterCategoryItemHorizontalBinding.inflate(inflater, parent, attachToParent)
	}

	override fun BaseViewHolder<AdapterCategoryItemHorizontalBinding, CategoryDomain>.bindActionTo(data : CategoryDomain) {
		binding.apply {
			tvCategory.text = data.name
			tvTime.text = data.nextEvent.convertFrom { DatePatterns.UTC }.convertTo { DatePatterns.DAY_MONTH_YEAR_HOUR_2 }
			image.setOnClickListener { onItemClick(bindingAdapterPosition) }
			Glide.with(holderContext)
				.load(data.photo)
				.into(image)
		}
	}

}
