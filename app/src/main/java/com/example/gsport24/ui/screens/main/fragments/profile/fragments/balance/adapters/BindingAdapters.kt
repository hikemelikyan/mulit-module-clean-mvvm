package com.example.gsport24.ui.screens.main.fragments.profile.fragments.balance.adapters

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gsport24.domain.entities.PaymentMethodDomain
import com.example.gsport24.domain.entities.TransactionDomain
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object BindingAdapters {

	@JvmStatic
	@BindingAdapter("payment_method_items")
	fun setPaymentMethods(rv : RecyclerView, items : LiveData<List<PaymentMethodDomain>>) {
		val adapter = rv.adapter as ConcatAdapter
		items.observeForever { (adapter.adapters[1] as PaymentMethodsAdapter).setList(it) }
	}

	@JvmStatic
	@BindingAdapter("transaction_items")
	fun setTransactionsList(rv : RecyclerView, items : LiveData<PagingData<TransactionDomain>>) {
		val adapter = rv.adapter as TransactionsAdapter
		items.observeForever {
			GlobalScope.launch {
				adapter.submitData(it)
			}
		}
	}

}