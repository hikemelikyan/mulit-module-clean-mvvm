package com.example.gsport24.di

import com.example.gsport24.Application
import com.example.gsport24.data.di.NetworkModule
import com.example.gsport24.firebase.di.FCMServiceComponent
import com.example.gsport24.root.di.RootModule
import com.example.gsport24.ui.screens.authorization.di.AuthComponent
import com.example.gsport24.ui.screens.authorization.fragments.signIn.di.SignInComponent
import com.example.gsport24.ui.screens.authorization.fragments.signUp.createPassword.di.CreatePasswordComponent
import com.example.gsport24.ui.screens.authorization.fragments.signUp.emailVerification.di.EmailVerificationComponent
import com.example.gsport24.ui.screens.authorization.fragments.signUp.phoneVerification.di.PhoneVerificationComponent
import com.example.gsport24.ui.screens.main.di.MainComponent
import com.example.gsport24.ui.screens.main.fragments.categories.categoriesList.di.CategoryPageComponent
import com.example.gsport24.ui.screens.main.fragments.home.di.HomeFragmentComponent
import com.example.gsport24.ui.screens.main.fragments.menu.di.NewsFragmentComponent
import com.example.gsport24.ui.screens.main.fragments.notifications.di.NotificationsComponent
import com.example.gsport24.ui.screens.main.fragments.profile.di.ProfileTabComponent
import com.example.gsport24.ui.screens.main.fragments.profile.fragments.balance.di.BalanceComponent
import com.example.gsport24.ui.screens.main.fragments.profile.fragments.changePassword.di.ChangePasswordComponent
import com.example.gsport24.ui.screens.main.fragments.profile.fragments.passportInfo.di.PassportInfoComponent
import com.example.gsport24.ui.screens.main.fragments.profile.fragments.userInfo.di.UserInfoComponent
import kotlin.reflect.KClass

class DependencyInjectionStorage private constructor(private val app : Application) {

	companion object {

		fun initialize(app : Application) : DependencyInjectionStorage {
			return DependencyInjectionStorage(app)
		}
	}

	private val componentMap = mutableMapOf<KClass<*>, Any>()

	private val rootModule : RootModule = RootModule(app)
	private val netModule = NetworkModule()

	inline fun <reified T> get() : T = getComponent(T::class)

	@Suppress("UNCHECKED_CAST")
	fun <T> getComponent(kClass : KClass<*>) : T {
		val component = componentMap[kClass]
		return if (component == null) {
			val newComponent = provideComponent(kClass)
			componentMap[kClass] = newComponent
			newComponent as T
		} else {
			component as T
		}
	}

	private inline fun <reified T> getBase() : T = getBaseComponent(T::class)

	@Suppress("UNCHECKED_CAST")
	private fun <T> getBaseComponent(kClass : KClass<*>) : T {
		val component = componentMap[kClass]
		return if (component == null) {
			val newComponent = provideComponent(kClass)
			componentMap[kClass] = newComponent
			newComponent as T
		} else {
			component as T
		}
	}

	inline fun <reified T> release() = releaseComponent(T::class)

	fun releaseComponent(kClass : KClass<*>) {
		val component = componentMap[kClass]
		component?.let {
			componentMap.remove(kClass)
		} ?: return
	}

	private fun provideComponent(kClass : KClass<*>) : Any {
		return when (kClass) {
			AppComponent::class -> DaggerAppComponent.builder().withRootModule(rootModule).withNetworkModule(netModule).build()
			FCMServiceComponent::class -> getBase<AppComponent>().firebaseMessagingServiceComponent.build()
			AuthComponent::class -> getBase<AppComponent>().authComponent.build()
			MainComponent::class -> getBase<AppComponent>().mainComponent.build()
			SignInComponent::class -> getBase<AuthComponent>().signInComponent.build()
			EmailVerificationComponent::class -> getBase<AuthComponent>().emailVerificationComponent.build()
			PhoneVerificationComponent::class -> getBase<AuthComponent>().phoneVerificationComponent.build()
			CreatePasswordComponent::class -> getBase<AuthComponent>().createPasswordComponent.build()
			CategoryPageComponent::class -> getBase<MainComponent>().categoryPageComponent.build()
			HomeFragmentComponent::class -> getBase<MainComponent>().homeFragmentComponent.build()
			ProfileTabComponent::class -> getBase<MainComponent>().profileTabComponent.build()
			NotificationsComponent::class -> getBase<MainComponent>().notificationsComponent.build()
			UserInfoComponent::class -> getBase<ProfileTabComponent>().userInfoComponent.build()
			BalanceComponent::class -> getBase<ProfileTabComponent>().userBalanceComponent.build()
			PassportInfoComponent::class -> getBase<UserInfoComponent>().passportInfoComponent.build()
			ChangePasswordComponent::class -> getBase<UserInfoComponent>().changePasswordComponent.build()
			NewsFragmentComponent::class -> getBase<MainComponent>().newsFragmentComponent.build()
			else -> throw UnsupportedOperationException("This component is not supported: ${kClass.simpleName}")
		}
	}

}