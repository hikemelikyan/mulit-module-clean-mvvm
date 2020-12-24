package com.example.gsport24.ui.screens.main

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gsport24.R
import com.example.gsport24.databinding.ActivityMainBinding
import com.example.gsport24.mvvm.ui.BaseRequestActivity
import com.example.gsport24.mvvm.vm.ViewCommand
import com.example.gsport24.root.ext.format
import com.example.gsport24.shared.BottomNavigationView

class MainActivity : BaseRequestActivity<ActivityMainBinding, MainViewModel>() {

	private lateinit var fragments : List<NavHostFragment>

	override val inflate : (LayoutInflater) -> ActivityMainBinding
		get() = ActivityMainBinding::inflate

	override val viewModelType : Class<MainViewModel>
		get() = MainViewModel::class.java

	override fun initView(binding : ActivityMainBinding, viewModel : MainViewModel) {
		setSupportActionBar(binding.toolbar)
		binding.apply {
			initFragmentPager()
			setViewModel(viewModel)
			lifecycleOwner = this@MainActivity

			tvBalance.text = 25000.format()
			tvBalance.setOnClickListener {
				if (viewModel.isBalanceHidden()) {
					viewModel.showBalance()
				} else {
					viewModel.hideBalance()
				}
			}

			bottomNavigation.addItemCallback(object : BottomNavigationView.ItemCallback {
				override fun onItemSelected(item : BottomNavigationView.NavItem) {
					fragmentContainerMain.setCurrentItem(item.ordinal, true)
				}

				override fun onItemReSelected(item : BottomNavigationView.NavItem) {
					while (fragments[fragmentContainerMain.currentItem].navController.popBackStack()){
						fragments[fragmentContainerMain.currentItem].navController.popBackStack()
					}
				}
			})
		}

	}

	override fun proceedViewCommand(command : ViewCommand) {

	}

	private fun ActivityMainBinding.initFragmentPager() {
		fragmentContainerMain.isUserInputEnabled = false
		fragments = initPagerFragments()
		fragmentContainerMain.adapter = PagerAdapter(fragments, supportFragmentManager, lifecycle)
	}

	private fun initPagerFragments() : List<NavHostFragment> {
		return arrayListOf(
			NavHostFragment.create(R.navigation.nav_home),
			NavHostFragment.create(R.navigation.nav_category),
			NavHostFragment.create(R.navigation.nav_notifications),
			NavHostFragment.create(R.navigation.nav_profile),
			NavHostFragment.create(R.navigation.nav_menu),
		)
	}

	private inner class PagerAdapter(
		private val list : List<NavHostFragment>,
		fragmentManager : FragmentManager,
		lifecycle : Lifecycle
	) : FragmentStateAdapter(fragmentManager, lifecycle) {

		override fun getItemCount() : Int {
			return list.size
		}

		override fun createFragment(position : Int) : Fragment {
			return list[position]
		}
	}

	fun setToolbarTitle(title : String, isIconVisible : Boolean = false, isBalanceVisible : Boolean = false) {
		mBinding.apply {
			tvBalance.visibility = if (isBalanceVisible) View.VISIBLE else View.GONE
			ivLogo.visibility = if (isIconVisible) View.VISIBLE else View.GONE
			tvTitle.text = title
		}
	}

	override fun onBackPressed() {
		if (!fragments[mBinding.fragmentContainerMain.currentItem].findNavController().popBackStack()) {
			super.onBackPressed()
		}
	}
}