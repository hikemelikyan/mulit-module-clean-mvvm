package com.example.gsport24.mvvm.ui

import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.example.gsport24.databinding.AdapterTitleItemBinding
import com.example.gsport24.root.ext.inflater

class TitleAdapter(
	@StringRes private val resId : Int,
	@StringRes private val actionResId : Int? = null,
	private val action : (() -> Unit)? = null
) : RecyclerView.Adapter<TitleAdapter.TitleViewHolder>() {

	override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : TitleViewHolder {
		return TitleViewHolder(AdapterTitleItemBinding.inflate(parent.context.inflater(), parent, false))
	}

	override fun onBindViewHolder(holder : TitleViewHolder, position : Int) {
		holder.apply {
			actionResId = this@TitleAdapter.actionResId
			action = this@TitleAdapter.action
			bind(resId)
		}
	}

	override fun getItemCount() : Int {
		return 1
	}

	inner class TitleViewHolder(binding : AdapterTitleItemBinding) : BaseViewHolder<AdapterTitleItemBinding, Int>(binding) {

		@StringRes
		var actionResId : Int? = null
		var action : (() -> Unit)? = null

		override fun bind(data : Int) {
			actionResId?.let {
				binding.action.text = holderContext.getString(it)
				binding.action.setOnClickListener { action?.invoke() }
			}
			binding.title.text = holderContext.getString(data)
		}
	}
}