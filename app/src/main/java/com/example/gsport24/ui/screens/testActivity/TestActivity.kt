package com.example.gsport24.ui.screens.testActivity

import android.view.LayoutInflater
import com.example.gsport24.databinding.ActivityTestBinding
import com.example.gsport24.mvvm.ui.BaseRequestActivity
import com.example.gsport24.mvvm.vm.ViewCommand
import com.example.gsport24.ui.screens.testActivity.adapters.CategoriesAdapter
import com.example.gsport24.ui.screens.main.fragments.home.adapters.CategoriesAdapterHorizontal

class TestActivity : BaseRequestActivity<ActivityTestBinding, TestActivityViewModel>() {
    private val adapter: CategoriesAdapter by lazy { CategoriesAdapter(::onItemClick) }
    private val horizontalAdapter: CategoriesAdapterHorizontal by lazy { CategoriesAdapterHorizontal() }

    override val viewModelType: Class<TestActivityViewModel>
        get() = TestActivityViewModel::class.java

    override val inflate: (LayoutInflater) -> ActivityTestBinding
        get() = ActivityTestBinding::inflate

    override fun proceedViewCommand(command: ViewCommand) {

    }

    override fun initView(binding: ActivityTestBinding, viewModel: TestActivityViewModel) {
        binding.viewModel = viewModel
        binding.rvCategories.adapter = adapter
        /*binding.rvCategoriesHorizontal.apply {
            layoutManager = LinearLayoutManager(
                this@TestActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = horizontalAdapter
            addOnScrollListener(JumpScrollListener(horizontalAdapter))
        }*/
    }

    private fun onItemClick(position: Int) {
        showToast(position.toString())
    }

}