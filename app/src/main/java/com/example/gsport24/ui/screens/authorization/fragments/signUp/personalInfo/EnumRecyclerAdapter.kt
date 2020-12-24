package com.example.gsport24.ui.screens.authorization.fragments.signUp.personalInfo

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.gsport24.databinding.AdapterEnumItemBinding
import com.example.gsport24.mvvm.ui.BaseListAdapter
import com.example.gsport24.mvvm.ui.BaseViewHolder
import com.example.gsport24.root.utils.DifItem
import com.example.gsport24.root.utils.getDiffCallback

class EnumRecyclerAdapter(private val onItemClick : (EnumRecyclerItem) -> Unit) : BaseListAdapter<EnumRecyclerItem, AdapterEnumItemBinding>(getDiffCallback()) {

	override fun inflate(inflater : LayoutInflater, parent : ViewGroup?, attachToParent : Boolean) : AdapterEnumItemBinding {
		return AdapterEnumItemBinding.inflate(inflater, parent, attachToParent)
	}

	override fun BaseViewHolder<AdapterEnumItemBinding, EnumRecyclerItem>.bindActionTo(data : EnumRecyclerItem) {
		binding.text.text = data.name
		binding.text.setOnClickListener { onItemClick(data) }
	}

}

data class EnumRecyclerItem(
	val id : Int,
	val name : String,
	val isChecked : Boolean
) : DifItem<EnumRecyclerItem> {

	override fun areItemsTheSame(second : EnumRecyclerItem) : Boolean {
		return id == second.id
	}

	override fun areContentsTheSame(second : EnumRecyclerItem) : Boolean {
		return name == second.name
	}

}