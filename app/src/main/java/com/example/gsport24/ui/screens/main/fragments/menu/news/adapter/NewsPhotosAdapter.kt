package com.example.gsport24.ui.screens.main.fragments.menu.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.gsport24.databinding.AdapterPhotosPagerBinding
import com.example.gsport24.mvvm.ui.BaseListAdapter
import com.example.gsport24.mvvm.ui.BaseViewHolder
import com.example.gsport24.root.utils.getDiffCallback
import com.bumptech.glide.Glide

class NewsPhotosAdapter : BaseListAdapter<String, AdapterPhotosPagerBinding>(getDiffCallback()) {

	override fun inflate(inflater : LayoutInflater, parent : ViewGroup?, attachToParent : Boolean) : AdapterPhotosPagerBinding {
		return AdapterPhotosPagerBinding.inflate(inflater, parent, attachToParent)
	}

	override fun BaseViewHolder<AdapterPhotosPagerBinding, String>.bindActionTo(data : String) {
		binding.apply {
			Glide.with(holderContext)
				.load(data)
				.into(photo)
		}
	}

	fun setList(list : List<String>?) {
		submitList(list)
	}
}