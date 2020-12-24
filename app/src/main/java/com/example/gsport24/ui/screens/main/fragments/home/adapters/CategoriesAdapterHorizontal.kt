package com.example.gsport24.ui.screens.main.fragments.home.adapters

import android.animation.ValueAnimator
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import androidx.core.app.FrameMetricsAggregator
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.gsport24.BR
import com.example.gsport24.databinding.AdapterCategoryItemHorizontalBinding
import com.example.gsport24.domain.entities.CategoryDomain
import com.example.gsport24.mvvm.ui.BaseListAdapter
import com.example.gsport24.mvvm.ui.BaseViewHolder
import com.example.gsport24.root.ext.convertFrom
import com.example.gsport24.root.ext.convertTo
import com.example.gsport24.root.ext.dpToPx
import com.example.gsport24.root.utils.DatePatterns
import com.example.gsport24.root.utils.getDiffCallback
import com.example.gsport24.shared.fakers.ImageProvider
import com.example.gsport24.shared.util.JumpScrollListener

class CategoriesAdapterHorizontal : BaseListAdapter<CategoryDomain, AdapterCategoryItemHorizontalBinding>(getDiffCallback()), JumpScrollListener.JumpAdapter {

	private val translateProvider : TranslateProvider by lazy { TranslateProvider() }

	private val listOfImages = ImageProvider.provideImages()

	inner class TranslateProvider : BaseObservable() {

		var translationY = 0f
			@Bindable get
			set(value) {
				field = value
				notifyPropertyChanged(BR.translationY)
			}
	}

	override fun inflate(inflater : LayoutInflater, parent : ViewGroup?, attachToParent : Boolean) : AdapterCategoryItemHorizontalBinding {
		return AdapterCategoryItemHorizontalBinding.inflate(inflater, parent, attachToParent)
	}

	override fun BaseViewHolder<AdapterCategoryItemHorizontalBinding, CategoryDomain>.bindActionTo(data : CategoryDomain) {
		binding.apply {

			item = translateProvider
			tvCategory.text = data.name
			tvTime.text = data.nextEvent.convertFrom { DatePatterns.UTC }.convertTo { DatePatterns.DAY_MONTH_YEAR_HOUR_2 }
			Glide.with(holderContext)
				.load(data.photo)
				.addListener(object : RequestListener<Drawable> {
					override fun onLoadFailed(e : GlideException?, model : Any?, target : Target<Drawable>?, isFirstResource : Boolean) : Boolean {
						return false
					}

					override fun onResourceReady(resource : Drawable?, model : Any?, target : Target<Drawable>?, dataSource : DataSource?, isFirstResource : Boolean) : Boolean {
						resource?.toBitmap()?.let {
							val palette = Palette.from(it)
							palette.addTarget(androidx.palette.graphics.Target.DARK_VIBRANT)
							palette.generate {
								it?.vibrantSwatch?.rgb?.let {
									image.setShadowColor(it)
									image.setShadowRadius(10)
								}
							}
						}
						return false
					}

				})
				.into(image)
		}
	}

	override fun down() {
		ValueAnimator.ofFloat(15.dpToPx().toFloat(), 0f)
			.apply {
				interpolator = BounceInterpolator()
				addUpdateListener {
					translateProvider.translationY = it.animatedValue as Float
				}
				duration = 400L
			}.start()
	}

	override fun up() {
		ValueAnimator.ofFloat(0f, 15.dpToPx().toFloat())
			.apply {
				interpolator = LinearInterpolator()
				addUpdateListener {
					translateProvider.translationY = it.animatedValue as Float
				}
				duration = FrameMetricsAggregator.ANIMATION_DURATION.toLong()
			}.start()
	}
}