package com.example.gsport24.shared.util

import androidx.recyclerview.widget.RecyclerView

class JumpScrollListener(
    private val adapter : JumpAdapter
) : RecyclerView.OnScrollListener() {

	var scrollStart = 0f

	override fun onScrollStateChanged(recyclerView : RecyclerView, newState : Int) {
		super.onScrollStateChanged(recyclerView, newState)
		when (newState) {
            RecyclerView.SCROLL_STATE_IDLE -> {
                adapter.down()
            }
            RecyclerView.SCROLL_STATE_DRAGGING -> {
	            adapter.up()
            }
		}
	}

	override fun onScrolled(recyclerView : RecyclerView, dx : Int, dy : Int) {
		super.onScrolled(recyclerView, dx, dy)
//		adapter.update(abs(recyclerView.computeHorizontalScrollOffset() - scrollStart) / (recyclerView.computeHorizontalScrollRange()).toFloat())
	}

	interface JumpAdapter {

		fun down()

		fun up()
	}

}