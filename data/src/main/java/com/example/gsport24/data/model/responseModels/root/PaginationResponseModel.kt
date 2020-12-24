package com.example.gsport24.data.model.responseModels.root

open class PaginationResponseModel<T>(
    val pageCount:Int,
    val itemCount:Int,
    val data:List<T>?
)