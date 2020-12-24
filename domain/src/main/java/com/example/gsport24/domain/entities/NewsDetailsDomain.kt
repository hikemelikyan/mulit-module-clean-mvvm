package com.example.gsport24.domain.entities

import com.example.gsport24.data.root.Entity

data class NewsDetailsDomain(
	val id : Int,
	val title : String,
	val description : String,
	val createdDate : String,
	val photos : List<String>
) : Entity