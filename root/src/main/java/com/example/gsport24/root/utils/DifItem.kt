package com.example.gsport24.root.utils

interface DifItem<T> {

	fun areItemsTheSame(second : T) : Boolean
	fun areContentsTheSame(second : T) : Boolean

}
