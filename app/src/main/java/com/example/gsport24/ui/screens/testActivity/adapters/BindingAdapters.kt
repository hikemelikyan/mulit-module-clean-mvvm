package com.example.gsport24.ui.screens.testActivity.adapters

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.gsport24.domain.entities.NotificationDomain

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("category_items")
    fun setAdapterItems(rv:RecyclerView,items: LiveData<List<NotificationDomain>>){
        val adapter = rv.adapter as CategoriesAdapter
        items.observeForever { adapter.submitList(it) }
    }

  /*  @JvmStatic
    @BindingAdapter("horizontal_category_items")
    fun setHorizontalAdapterItems(rv:RecyclerView,items: LiveData<List<NotificationDomain>>){
        val adapter = rv.adapter as CategoriesAdapterHorizontal
        items.observeForever { adapter.submitList(it) }
    }*/
}