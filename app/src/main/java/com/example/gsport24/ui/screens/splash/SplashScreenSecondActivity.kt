package com.example.gsport24.ui.screens.splash

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.lifecycleScope
import com.example.gsport24.Application
import com.example.gsport24.databinding.ActivitySplashScreenBinding
import com.example.gsport24.root.di.RootModule
import com.example.gsport24.root.utils.SharedPreferencesHelper
import com.example.gsport24.ui.screens.authorization.AuthorizationActivity
import com.example.gsport24.ui.screens.main.MainActivity
import com.example.gsport24.ui.screens.splash.di.DaggerSplashScreenComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashScreenSecondActivity : AppCompatActivity() {

	@Inject
	lateinit var mShared : SharedPreferencesHelper

	override fun onCreate(savedInstanceState : Bundle?) {
		window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
		super.onCreate(savedInstanceState)
		DaggerSplashScreenComponent.builder()
			.rootModule(RootModule(Application.getInstance()))
			.build().inject(this)
		val mBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
		setContentView(mBinding.root)
		lifecycleScope.launch {
			delay(2000)
			val options =
				ActivityOptionsCompat.makeSceneTransitionAnimation(this@SplashScreenSecondActivity, mBinding.appIcon, mBinding.appIcon.transitionName)
			if (mShared.getAuthToken() == null) {
				startActivity(Intent(this@SplashScreenSecondActivity, AuthorizationActivity::class.java), options.toBundle())
				delay(1500)
				finishAfterTransition()
			} else {
				startActivity(Intent(this@SplashScreenSecondActivity, MainActivity::class.java), options.toBundle())
				delay(1500)
				finishAfterTransition()
			}
		}
	}
}