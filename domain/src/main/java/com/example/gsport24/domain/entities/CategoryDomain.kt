package com.example.gsport24.domain.entities

import com.example.gsport24.data.root.Entity
import com.example.gsport24.root.utils.DifItem

data class CategoryDomain(
	val id : Int,
	val name : String,
	val photo : String,
	val nextEvent : String
) : Entity, DifItem<CategoryDomain> {

	override fun areItemsTheSame(second : CategoryDomain) : Boolean {
		return id == second.id
	}

	override fun areContentsTheSame(second : CategoryDomain) : Boolean {
		return id == second.id &&
				name == second.name &&
				photo == second.photo &&
				nextEvent == second.nextEvent
	}

}