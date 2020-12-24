package com.example.gsport24.ui.screens.authorization

import android.view.LayoutInflater
import androidx.navigation.findNavController
import com.example.gsport24.Application
import com.example.gsport24.R
import com.example.gsport24.databinding.ActivityAuthorizationBinding
import com.example.gsport24.mvvm.ui.BaseActivity
import com.example.gsport24.root.ext.withFullScreen
import com.example.gsport24.ui.screens.authorization.di.AuthComponent
import com.example.gsport24.ui.screens.authorization.fragments.AuthTypeFragment

class AuthorizationActivity : BaseActivity<ActivityAuthorizationBinding>() {

	private var finishOnBack = true

	override val inflate : (LayoutInflater) -> ActivityAuthorizationBinding
		get() = ActivityAuthorizationBinding::inflate

	override fun initView(binding : ActivityAuthorizationBinding) {
		withFullScreen()
		Application.getInstance().injectionStorage.get<AuthComponent>().inject(this)
		binding.apply {
			val navController = findNavController(R.id.fragment_container)
			navController.addOnDestinationChangedListener { _, destination, _ ->
				setSupportActionBar(mBinding.toolbar)
				finishOnBack = destination.label == AuthTypeFragment::class.simpleName
				supportActionBar?.setDisplayHomeAsUpEnabled(destination.label != AuthTypeFragment::class.simpleName)
			}
		}
	}

	override fun onSupportNavigateUp() : Boolean {
		onBackPressed()
		return true
	}

	override fun onBackPressed() {
		if (finishOnBack) {
			finish()
		} else {
			super.onBackPressed()
		}
	}
}