package com.example.gsport24.ui.screens.main.fragments.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.gsport24.databinding.AdapterHomeBannerItemBinding
import com.example.gsport24.databinding.AdapterHomeBannerPagerItemBinding
import com.example.gsport24.mvvm.ui.BaseListAdapter
import com.example.gsport24.mvvm.ui.BaseViewHolder
import com.example.gsport24.root.utils.DifItem
import com.example.gsport24.root.utils.getDiffCallback
import com.bumptech.glide.Glide

class HomePagerAdapter : BaseListAdapter<HomePagerAdapter.PagerItems, AdapterHomeBannerPagerItemBinding>(getDiffCallback()) {

	private var prevPosition = 0

	override fun inflate(inflater : LayoutInflater, parent : ViewGroup?, attachToParent : Boolean) : AdapterHomeBannerPagerItemBinding {
		return AdapterHomeBannerPagerItemBinding.inflate(inflater, parent, attachToParent)
	}

	override fun BaseViewHolder<AdapterHomeBannerPagerItemBinding, PagerItems>.bindActionTo(data : PagerItems) {
		if (binding.pager.adapter == null) {
			val adapter = HomeBannerAdapter()
			binding.pager.adapter = adapter
			binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
				override fun onPageSelected(position : Int) {
					super.onPageSelected(position)
					prevPosition = position
				}
			})
			binding.dotsIndicator.setViewPager2(binding.pager)
			adapter.submitList(data.items)
			binding.pager.setCurrentItem(prevPosition, false)
		}
	}

	fun setList(list : List<String>) {
		submitList(listOf(PagerItems(list)))
	}

	inner class PagerItems(val items : List<String>) : DifItem<PagerItems> {

		override fun areItemsTheSame(second : PagerItems) : Boolean {
			return items.size == second.items.size
		}

		override fun areContentsTheSame(second : PagerItems) : Boolean {
			var areContentsTheSame = true
			items.forEachIndexed { index, item ->
				if (item != second.items[index]) {
					areContentsTheSame = false
					return@forEachIndexed
				}
			}
			return areContentsTheSame
		}
	}

	class HomeBannerAdapter : BaseListAdapter<String, AdapterHomeBannerItemBinding>(getDiffCallback()) {

		override fun inflate(inflater : LayoutInflater, parent : ViewGroup?, attachToParent : Boolean) : AdapterHomeBannerItemBinding {
			return AdapterHomeBannerItemBinding.inflate(inflater, parent, attachToParent)
		}

		override fun BaseViewHolder<AdapterHomeBannerItemBinding, String>.bindActionTo(data : String) {
			binding.apply {
				Glide.with(holderContext)
					.load(data)
					.into(photo)
			}
		}
	}
}