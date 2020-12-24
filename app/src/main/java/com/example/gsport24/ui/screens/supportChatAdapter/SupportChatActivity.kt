package com.example.gsport24.ui.screens.supportChatAdapter

import android.view.LayoutInflater
import com.example.gsport24.databinding.ActivitySupportChatBinding
import com.example.gsport24.mvvm.ui.BaseRequestActivity
import com.example.gsport24.mvvm.vm.ViewCommand
import com.example.gsport24.ui.screens.supportChatAdapter.adapters.SupportChatAdapter

class SupportChatActivity: BaseRequestActivity<ActivitySupportChatBinding, SupportChatViewModel>() {
    override val viewModelType: Class<SupportChatViewModel>
        get() = SupportChatViewModel::class.java
    override val inflate: (LayoutInflater) -> ActivitySupportChatBinding
        get() = ActivitySupportChatBinding::inflate

    override fun initView(binding: ActivitySupportChatBinding, viewModel: SupportChatViewModel) {
        binding.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            setViewModel(viewModel)
            viewModel.getMessages()
            ivAttachFile.setOnClickListener { showToast("Attach file") }
            ivAttachPhoto.setOnClickListener { showToast("Attach image") }
            chatRecycler.adapter = SupportChatAdapter()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun proceedViewCommand(command: ViewCommand) {

    }
}