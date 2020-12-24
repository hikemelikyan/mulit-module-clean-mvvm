package com.example.gsport24.ui.screens.authorization.di

import com.example.gsport24.ui.screens.authorization.AuthorizationActivity
import com.example.gsport24.ui.screens.authorization.fragments.signIn.di.SignInComponent
import com.example.gsport24.ui.screens.authorization.fragments.signUp.createPassword.di.CreatePasswordComponent
import com.example.gsport24.ui.screens.authorization.fragments.signUp.emailVerification.di.EmailVerificationComponent
import com.example.gsport24.ui.screens.authorization.fragments.signUp.phoneVerification.di.PhoneVerificationComponent
import dagger.Subcomponent

@Subcomponent
interface AuthComponent {

	@Subcomponent.Builder
	interface Builder {
		fun build(): AuthComponent
	}

	val phoneVerificationComponent : PhoneVerificationComponent.Builder
	val emailVerificationComponent : EmailVerificationComponent.Builder
	val createPasswordComponent : CreatePasswordComponent.Builder
	val signInComponent : SignInComponent.Builder

	fun inject(target : AuthorizationActivity)
}