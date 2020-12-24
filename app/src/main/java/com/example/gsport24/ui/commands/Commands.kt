package com.example.gsport24.ui.commands

import androidx.annotation.StringRes
import androidx.paging.PagingData
import com.example.gsport24.domain.entities.NewsDomain
import com.example.gsport24.mvvm.vm.ViewCommand

sealed class Commands {

	/**
	 * base UI commands
	 * */
	object NetworkError : ViewCommand
	class ShowMessage(@StringRes val resId : Int) : ViewCommand
	class ShowMessageText(val errorMessage : String) : ViewCommand
	object StateLoading : ViewCommand
	object StateEmpty : ViewCommand
	object ReLaunchApp : ViewCommand

	/**
	 * signUp global
	 * */
	class CodeSent(val id : Int) : ViewCommand

	/**
	 * phone verification
	 */
	class PhoneVerified(val codeId : Int) : ViewCommand

	/**
	 * email verification
	 * */
	class EmailVerified(val codeId : Int) : ViewCommand

	/**
	 * sign in and create password
	 * */
	object NavigateToMain : ViewCommand

	/**
	 * home fragment
	 * */
	class InitNewsList(val list : PagingData<NewsDomain>) : ViewCommand

	/**
	 * profile info
	 * */
	object UserInfoUpdated : ViewCommand
	object PassportInfoUpdated : ViewCommand
	object PasswordUpdated : ViewCommand
}