package com.example.gsport24.ui.screens.main.fragments.categories.categoriesList.adapters

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.gsport24.domain.entities.CategoryDomain

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("category_items")
    fun setAdapterItems(rv:RecyclerView,items: LiveData<List<CategoryDomain>>){
        val adapter = rv.adapter as CategoriesAdapter
        items.observeForever { adapter.submitList(it) }
    }

}