package com.example.gsport24.ui.screens.main.fragments.profile.fragments.balance.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.gsport24.databinding.AdapterTransactionItemBinding
import com.example.gsport24.domain.entities.TransactionDomain
import com.example.gsport24.mvvm.ui.BasePagingAdapter
import com.example.gsport24.mvvm.ui.BaseViewHolder
import com.example.gsport24.root.ext.convertServerDateTo
import com.example.gsport24.root.ext.format
import com.example.gsport24.root.utils.DatePatterns
import com.example.gsport24.root.utils.getDiffCallback

class TransactionsAdapter : BasePagingAdapter<TransactionDomain, AdapterTransactionItemBinding>(getDiffCallback()) {

	override fun inflate(inflater : LayoutInflater, parent : ViewGroup?, attachToParent : Boolean) : AdapterTransactionItemBinding {
		return AdapterTransactionItemBinding.inflate(inflater, parent, attachToParent)
	}

	override fun BaseViewHolder<AdapterTransactionItemBinding, TransactionDomain>.bindActionTo(data : TransactionDomain) {
		binding.apply {
			tvTransactionDate.text = data.date.convertServerDateTo { DatePatterns.DAY_MONTH_YEAR }
			// TODO: 12/22/2020 add to string resources
			tvTransactionType.text = when {
				data.isDeposit -> {
					tvTransactionAmount.text = String.format("+ %s", data.amount.format())
					"Пополнение баланса"
				}
				data.isCashOut -> {
					tvTransactionAmount.text = String.format("- %s", data.amount.format())
					"Вывод средств"
				}
				data.isBonus -> {
					tvTransactionAmount.text = String.format("+ %s", data.amount.format())
					"Бонус"
				}
				data.isBetting -> {
					tvTransactionAmount.text = String.format("- %s", data.amount.format())
					"Ставка"
				}
				else -> {
					tvTransactionAmount.text = String.format("- %s", data.amount.format())
					"Ставка"
				}
			}
		}
	}
}