package com.example.gsport24.domain.entities

import com.example.gsport24.data.root.Entity
import com.example.gsport24.root.utils.DifItem

data class NewsDomain(
	val id : Int,
	val title : String,
	val photo : String,
	val date : String
) : Entity, DifItem<NewsDomain> {

	override fun areItemsTheSame(second : NewsDomain) : Boolean {
		return id == second.id
	}

	override fun areContentsTheSame(second : NewsDomain) : Boolean {
		return date == second.date &&
				photo == second.photo &&
				title == second.title
	}

}