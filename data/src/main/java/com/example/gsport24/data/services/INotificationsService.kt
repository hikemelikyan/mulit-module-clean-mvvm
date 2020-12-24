package com.example.gsport24.data.services

import com.example.gsport24.data.model.requestModels.PaginationRequestModel
import com.example.gsport24.data.model.responseModels.NotificationResponseModel
import com.example.gsport24.data.model.responseModels.root.PaginationResponseModel
import com.example.gsport24.data.model.responseModels.root.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface INotificationsService {

	@POST("/api/Notification/GetList")
	suspend fun getNotificationsList(@Body model : PaginationRequestModel) : Response<PaginationResponseModel<NotificationResponseModel>>

}