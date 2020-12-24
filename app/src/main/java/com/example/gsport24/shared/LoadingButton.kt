package com.example.gsport24.shared

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.gsport24.R
import com.example.gsport24.data.root.UIState
import com.example.gsport24.databinding.LayoutLoadingButtonBinding
import com.example.gsport24.root.ext.hide
import com.example.gsport24.root.ext.inflater
import com.example.gsport24.root.ext.show

class LoadingButton @JvmOverloads constructor(
	context : Context, attrs : AttributeSet? = null, defStyleAttr : Int = 0
) : FrameLayout(context, attrs, defStyleAttr), View.OnClickListener {

	companion object {

		@JvmStatic
		@BindingAdapter("stateHandler")
		fun setStateHandler(button : LoadingButton, stateHandler : LiveData<UIState>) {
			button.setStateHandler(stateHandler)
		}
	}

	private val mBinding : LayoutLoadingButtonBinding
	private var text : CharSequence
	private var textColor : Int
	private var textSize : Float
	private var loadingColor : Int
	private var backgroundDrawable : Int
	private val stateObserver = Observer<UIState> {
		if (it == UIState.LOADING) {
			showLoading()
		} else {
			hideLoading()
		}
	}
	private var stateLiveData : LiveData<UIState>? = null
	private var onClickListener : OnClickListener? = null

	init {
		layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
		mBinding = LayoutLoadingButtonBinding.inflate(context.inflater(), this, false)
		val incomingValues = context.obtainStyledAttributes(attrs, R.styleable.LoadingButton)
		text = incomingValues.getText(R.styleable.LoadingButton_text)
		textColor =
			incomingValues.getColor(R.styleable.LoadingButton_textColor, ContextCompat.getColor(context, android.R.color.white))
		textSize =
			incomingValues.getDimension(R.styleable.LoadingButton_textSize, context.resources.getDimension(R.dimen.text_size_normal))
		loadingColor =
			incomingValues.getColor(R.styleable.LoadingButton_loadingColor, ContextCompat.getColor(context, android.R.color.white))
		backgroundDrawable = incomingValues.getInt(R.styleable.LoadingButton_backgroundDrawable, 0)
		makeButton()
		super.setOnClickListener(this)
		incomingValues.recycle()
	}

	private fun makeButton() {
		mBinding.apply {
			button.apply {
				text = this@LoadingButton.text
				setTextColor(textColor)
				setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
				setBackgroundResource(backgroundDrawable)
			}
			progress.indeterminateTintList = ColorStateList.valueOf(loadingColor)
		}
		addView(mBinding.root)
	}

	private fun showLoading() {
		isEnabled = false
		mBinding.apply {
			progress.show()
			button.text = ""
		}
	}

	private fun hideLoading() {
		isEnabled = true
		mBinding.apply {
			progress.hide()
			button.text = text
		}
		stateLiveData?.removeObserver(stateObserver)
	}

	fun setText(text : String) {
		this.text = text
		invalidateInternal()
	}

	fun setTextSize(size : Float) {
		textSize = size
		invalidateInternal()
	}

	fun setTextSize(@DimenRes resId : Int) {
		textSize = context.resources.getDimension(resId)
		invalidateInternal()
	}

	fun setTextColor(color : Int) {
		textColor = color
		invalidateInternal()
	}

	fun setLoadingColor(color : Int) {
		loadingColor = color
		invalidateInternal()
	}

	fun setBackgroundDrawable(@DrawableRes resId : Int) {
		backgroundDrawable = resId
		invalidateInternal()
	}

	fun isLoading(isLoading : Boolean) {
		if (isLoading) {
			showLoading()
		} else {
			hideLoading()
		}
	}

	private fun invalidateInternal() {
		removeAllViews()
		makeButton()
	}

	override fun setOnClickListener(l : OnClickListener?) {
		onClickListener = l
	}

	fun setStateHandler(liveData : LiveData<UIState>) {
		stateLiveData = liveData
	}

	override fun onClick(p0 : View?) {
		stateLiveData?.observeForever(stateObserver)
		onClickListener?.onClick(this)
	}
}