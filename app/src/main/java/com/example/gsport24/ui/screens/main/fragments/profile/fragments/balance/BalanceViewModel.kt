package com.example.gsport24.ui.screens.main.fragments.profile.fragments.balance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.example.gsport24.Application
import com.example.gsport24.data.model.requestModels.PaginationRequestModel
import com.example.gsport24.domain.entities.PaymentMethodDomain
import com.example.gsport24.domain.entities.TransactionDomain
import com.example.gsport24.domain.useCase.transactionsHistory.GetTransactionsHistoryAndPaymentMethodsUseCase
import com.example.gsport24.mvvm.vm.BaseViewModel
import com.example.gsport24.ui.screens.main.fragments.profile.fragments.balance.di.BalanceComponent
import javax.inject.Inject

class BalanceViewModel : BaseViewModel() {

	@Inject
	lateinit var useCase : GetTransactionsHistoryAndPaymentMethodsUseCase

	private val _paymentMethods : MutableLiveData<List<PaymentMethodDomain>> by lazy { MutableLiveData() }
	val paymentMethods : LiveData<List<PaymentMethodDomain>>
		get() = _paymentMethods

	private val _transactions : MutableLiveData<PagingData<TransactionDomain>> by lazy { MutableLiveData() }
	val transactions : LiveData<PagingData<TransactionDomain>>
		get() = _transactions

	init {
		inject()
		getPaymentMethods()
		getTransactionHistory()
	}

	private fun getPaymentMethods() {
		launchDefault {
			val result = useCase.getPaymentMethods()
			result doOnSuccess {
				_paymentMethods.postValue(it)
			}
		}
	}

	private fun getTransactionHistory() {
		launchDefault {
			val result = useCase.getTransactionHistory(PaginationRequestModel())
			result doOnSuccess {
				_transactions.postValue(it)
			}
		}
	}

	override fun inject() {
		Application.getInstance()
			.injectionStorage
			.get<BalanceComponent>()
			.inject(this)
	}

	override fun releaseInjection() {
		Application.getInstance()
			.injectionStorage
			.release<BalanceComponent>()
	}
}