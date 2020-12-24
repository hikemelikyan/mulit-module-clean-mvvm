package com.example.gsport24.ui.screens.main.fragments.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gsport24.databinding.AdapterHomeCategoriesRecyclerItemBinding
import com.example.gsport24.domain.entities.CategoryDomain
import com.example.gsport24.mvvm.ui.BaseListAdapter
import com.example.gsport24.mvvm.ui.BaseViewHolder
import com.example.gsport24.root.utils.DifItem
import com.example.gsport24.root.utils.getDiffCallback
import com.example.gsport24.shared.util.JumpScrollListener

class HomeCategoriesAdapter : BaseListAdapter<HomeCategoriesAdapter.CategoryItems, AdapterHomeCategoriesRecyclerItemBinding>(getDiffCallback()) {

	inner class CategoryItems(val items : List<CategoryDomain>) : DifItem<CategoryItems> {

		override fun areItemsTheSame(second : CategoryItems) : Boolean {
			return false
		}

		override fun areContentsTheSame(second : CategoryItems) : Boolean {
			return false
		}
	}

	fun setList(list : List<CategoryDomain>) {
		submitList(listOf(CategoryItems(list)))
	}

	override fun inflate(inflater : LayoutInflater, parent : ViewGroup?, attachToParent : Boolean) : AdapterHomeCategoriesRecyclerItemBinding {
		return AdapterHomeCategoriesRecyclerItemBinding.inflate(inflater, parent, attachToParent)
	}

	override fun BaseViewHolder<AdapterHomeCategoriesRecyclerItemBinding, CategoryItems>.bindActionTo(data : CategoryItems) {
		binding.apply {
			if (rvCategories.adapter == null) {
				val adapter = CategoriesAdapterHorizontal()
				rvCategories.layoutManager = GridLayoutManager(holderContext, 2, GridLayoutManager.VERTICAL, false)
				rvCategories.addOnScrollListener(JumpScrollListener(adapter))
				rvCategories.adapter = adapter
				adapter.submitList(data.items)
			}
		}
	}

}