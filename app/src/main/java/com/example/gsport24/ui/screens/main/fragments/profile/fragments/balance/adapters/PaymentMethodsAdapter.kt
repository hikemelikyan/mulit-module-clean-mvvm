package com.example.gsport24.ui.screens.main.fragments.profile.fragments.balance.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.gsport24.databinding.AdapterPaymentMethodItemBinding
import com.example.gsport24.databinding.AdapterPaymentMethodsHorizontalLayoutBinding
import com.example.gsport24.domain.entities.PaymentMethodDomain
import com.example.gsport24.mvvm.ui.BaseListAdapter
import com.example.gsport24.mvvm.ui.BaseViewHolder
import com.example.gsport24.root.utils.DifItem
import com.example.gsport24.root.utils.getDiffCallback

class PaymentMethodsAdapter : BaseListAdapter<PaymentMethodsAdapter.PaymentMethods, AdapterPaymentMethodsHorizontalLayoutBinding>(getDiffCallback()) {

	fun setList(list : List<PaymentMethodDomain>) {
		submitList(listOf(PaymentMethods(list)))
	}

	override fun inflate(inflater : LayoutInflater, parent : ViewGroup?, attachToParent : Boolean) : AdapterPaymentMethodsHorizontalLayoutBinding {
		return AdapterPaymentMethodsHorizontalLayoutBinding.inflate(inflater, parent, attachToParent)
	}

	override fun BaseViewHolder<AdapterPaymentMethodsHorizontalLayoutBinding, PaymentMethods>.bindActionTo(data : PaymentMethods) {
		val adapter = PaymentMethodsHorizontalAdapter()
		val snapHelper = LinearSnapHelper()
		snapHelper.attachToRecyclerView(binding.rvPaymentMethods)
		binding.rvPaymentMethods.layoutManager = LinearLayoutManager(holderContext,LinearLayoutManager.HORIZONTAL,false)
		binding.rvPaymentMethods.adapter = adapter
		adapter.submitList(data.list)
	}

	inner class PaymentMethodsHorizontalAdapter : BaseListAdapter<PaymentMethodDomain, AdapterPaymentMethodItemBinding>(getDiffCallback()) {

		override fun inflate(inflater : LayoutInflater, parent : ViewGroup?, attachToParent : Boolean) : AdapterPaymentMethodItemBinding {
			return AdapterPaymentMethodItemBinding.inflate(inflater, parent, attachToParent)
		}

		override fun BaseViewHolder<AdapterPaymentMethodItemBinding, PaymentMethodDomain>.bindActionTo(data : PaymentMethodDomain) {
			binding.apply {
				tvDate.text = data.givenDate
				tvUserName.text = data.userInfo
				tvCardNumbers.text = data.cardNumbers
			}
		}
	}

	class PaymentMethods(val list : List<PaymentMethodDomain>):DifItem<PaymentMethods>{

		override fun areItemsTheSame(second : PaymentMethods) : Boolean {
			return false
		}

		override fun areContentsTheSame(second : PaymentMethods) : Boolean {
			return false
		}

	}
}
