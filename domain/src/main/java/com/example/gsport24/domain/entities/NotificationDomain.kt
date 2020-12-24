package com.example.gsport24.domain.entities

import com.example.gsport24.data.root.Entity
import com.example.gsport24.root.utils.DifItem

data class NotificationDomain(
    val id : Int,
    val title : String,
    val description : String,
    val date : String,
    val type:Int
) : Entity, DifItem<NotificationDomain> {

    override fun areItemsTheSame(second : NotificationDomain) : Boolean {
        return id == second.id
    }

    override fun areContentsTheSame(second : NotificationDomain) : Boolean {
        return date == second.date
    }
}