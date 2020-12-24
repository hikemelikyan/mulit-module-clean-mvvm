package com.example.gsport24.ui.screens.splash

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.gsport24.root.ext.launch

class SplashScreenFirstActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onCreate(savedInstanceState)
        launch { SplashScreenSecondActivity::class.java }
        finish()
    }
}