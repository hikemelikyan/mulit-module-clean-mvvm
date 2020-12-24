package com.example.gsport24.ui.screens.main.fragments.home.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.gsport24.domain.entities.CategoryDomain
import com.example.gsport24.domain.entities.NewsDomain
import com.example.gsport24.root.ext.convertFrom
import com.example.gsport24.root.ext.convertTo
import com.example.gsport24.ui.screens.main.fragments.menu.news.adapter.NewsPhotosAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object BindingAdapters {

	@JvmStatic
	@BindingAdapter("horizontal_banner_items")
	fun setHorizontalBannerItems(rv : RecyclerView, items : LiveData<List<String>>) {
		val adapter = rv.adapter as ConcatAdapter
		items.observeForever { (adapter.adapters[0] as HomePagerAdapter).setList(it) }
	}

	@JvmStatic
	@BindingAdapter("horizontal_category_items")
	fun setHorizontalCategoryItems(rv : RecyclerView, items : LiveData<List<CategoryDomain>>) {
		val adapter = rv.adapter as ConcatAdapter
		items.observeForever { (adapter.adapters[2] as HomeCategoriesAdapter).setList(it.take(4)) }
	}

	@JvmStatic
	@BindingAdapter("news_items_main")
	fun setNewsItemsMain(rv : RecyclerView, items : LiveData<List<NewsDomain>>) {
		val adapter = rv.adapter as ConcatAdapter
		items.observeForever {
			GlobalScope.launch {
				(adapter.adapters[5] as NewsMainListAdapter).submitList(it)
			}
		}
	}

	@JvmStatic
	@BindingAdapter("news_items")
	fun setNewsItems(rv : RecyclerView, items : LiveData<PagingData<NewsDomain>>) {
		val adapter = rv.adapter as NewsListAdapter
		items.observeForever {
			GlobalScope.launch {
				adapter.submitData(it)
			}
		}
	}

	@JvmStatic
	@BindingAdapter("setNewsPhotos")
	fun setNewsPhotos(rv : ViewPager2, items : List<String>?) {
		val adapter = rv.adapter as NewsPhotosAdapter
		adapter.setList(items ?: arrayListOf())
	}

	@JvmStatic
	@BindingAdapter("setNewsDate")
	fun setNewsDate(textView : TextView, date : String?) {
		date?.let {
			textView.text =
				it.convertFrom { com.example.gsport24.root.utils.DatePatterns.UTC }.convertTo { com.example.gsport24.root.utils.DatePatterns.DAY_MONTH_YEAR }
		} ?: "Null"
	}
}