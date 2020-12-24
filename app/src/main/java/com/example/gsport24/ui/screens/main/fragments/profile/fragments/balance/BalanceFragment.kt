package com.example.gsport24.ui.screens.main.fragments.profile.fragments.balance

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gsport24.R
import com.example.gsport24.databinding.FragmentBalanceBinding
import com.example.gsport24.mvvm.ui.BaseRequestFragment
import com.example.gsport24.mvvm.ui.TitleAdapter
import com.example.gsport24.mvvm.vm.ViewCommand
import com.example.gsport24.ui.screens.main.fragments.profile.fragments.balance.adapters.PaymentMethodsAdapter
import com.example.gsport24.ui.screens.main.fragments.profile.fragments.balance.adapters.TransactionsAdapter

class BalanceFragment : BaseRequestFragment<FragmentBalanceBinding, BalanceViewModel>() {

	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentBalanceBinding
		get() = FragmentBalanceBinding::inflate

	override val viewModelType : Class<BalanceViewModel>
		get() = BalanceViewModel::class.java

	override fun initView(binding : FragmentBalanceBinding, viewModel : BalanceViewModel) {
		binding.apply {
			setViewModel(viewModel)
			tvFillIn.setOnClickListener {

			}
			tvCashOut.setOnClickListener {

			}
			rvPaymentMethods.layoutManager = LinearLayoutManager(requireContext())
			rvPaymentMethods.adapter = ConcatAdapter(
				TitleAdapter(R.string.your_cards, R.string.add) { showToast("Add card") },
				PaymentMethodsAdapter()
			)
			rvTransactions.layoutManager = LinearLayoutManager(requireContext())
			rvTransactions.adapter = TransactionsAdapter()
		}
	}

	override fun proceedViewCommand(command : ViewCommand) {

	}
}