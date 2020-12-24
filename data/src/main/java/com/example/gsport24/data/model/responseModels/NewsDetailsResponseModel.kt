package com.example.gsport24.data.model.responseModels

import com.example.gsport24.data.root.Entity

data class NewsDetailsResponseModel(
	val id : Int,
	val title : String,
	val description : String,
	val createdDate : String,
	val photos : List<String>
) : Entity