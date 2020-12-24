package com.example.gsport24.ui.screens.supportChatAdapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gsport24.data.root.UIState
import com.example.gsport24.mvvm.vm.BaseViewModel
import java.util.*

class SupportChatViewModel : BaseViewModel() {

    init {
        switchUIState(UIState.SUCCESS)
    }

    private val _messages: MutableLiveData<List<Any>> by lazy { MutableLiveData() }
    val messages: LiveData<List<Any>>
        get() = _messages

    fun getMessages() {
        val list = listOf(
            SentMessageModel(true),
            ReceivedMessageModel(UUID.randomUUID().mostSignificantBits.toInt()),
            SentMessageModel(),
            SentPhotoModel("https://static.addtoany.com/images/dracaena-cinnabari.jpg"),
            ReceivedPhotoModel("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg"),
            SentMessageModel(true),
            ReceivedMessageModel(UUID.randomUUID().mostSignificantBits.toInt()),
            SentMessageModel(),
            SentPhotoModel("https://static.addtoany.com/images/dracaena-cinnabari.jpg"),
            ReceivedPhotoModel("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg"),
            SentMessageModel(true),
            ReceivedMessageModel(UUID.randomUUID().mostSignificantBits.toInt()),
            SentMessageModel(),
            SentPhotoModel("https://static.addtoany.com/images/dracaena-cinnabari.jpg"),
            ReceivedPhotoModel("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg"),
            SentMessageModel(true),
            ReceivedMessageModel(UUID.randomUUID().mostSignificantBits.toInt()),
            SentMessageModel(),
            SentPhotoModel("https://static.addtoany.com/images/dracaena-cinnabari.jpg"),
            ReceivedPhotoModel("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg"),
            SentMessageModel(true),
            ReceivedMessageModel(UUID.randomUUID().mostSignificantBits.toInt()),
            SentMessageModel(),
            SentPhotoModel("https://static.addtoany.com/images/dracaena-cinnabari.jpg"),
            ReceivedPhotoModel("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg"),
        )

        _messages.postValue(list)

    }

    override fun inject() {
        TODO("Not yet implemented")
    }

    override fun releaseInjection() {
        TODO("Not yet implemented")
    }

}