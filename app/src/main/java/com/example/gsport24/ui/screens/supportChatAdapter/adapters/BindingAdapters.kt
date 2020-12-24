package com.example.gsport24.ui.screens.supportChatAdapter.adapters

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("message_items")
    fun setAdapterItems(rv: RecyclerView, items: LiveData<List<Any>>){
        val adapter = rv.adapter as SupportChatAdapter
        items.observeForever { adapter.submitList(it) }
    }
}