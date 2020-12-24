package com.example.gsport24.ui.screens.main.fragments.home.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gsport24.databinding.AdapterViewAllCategoriesItemBinding
import com.example.gsport24.mvvm.ui.BaseViewHolder
import com.example.gsport24.root.ext.inflater

class CategoriesSeeAllAdapter(
	private val onClick:()->Unit
) : RecyclerView.Adapter<CategoriesSeeAllAdapter.SeeAllViewHolder>() {

	inner class SeeAllViewHolder(binding : AdapterViewAllCategoriesItemBinding) : BaseViewHolder<AdapterViewAllCategoriesItemBinding, Int>(binding) {

		override fun bind(data : Int) {
			binding.tvSeeAll.setOnClickListener { onClick() }
		}
	}

	override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : SeeAllViewHolder {
		return SeeAllViewHolder(AdapterViewAllCategoriesItemBinding.inflate(parent.context.inflater(),parent,false))
	}

	override fun onBindViewHolder(holder : SeeAllViewHolder, position : Int) {
		holder.bind(0)
	}

	override fun getItemCount() : Int {
		return 1
	}
}