package com.example.gsport24.shared

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.drawable.toBitmap
import com.example.gsport24.R
import com.example.gsport24.databinding.LayoutBottomNavigationBinding
import com.example.gsport24.root.ext.getColorCompat
import com.example.gsport24.root.ext.getDrawableCompat
import com.example.gsport24.root.ext.hide
import com.example.gsport24.root.ext.inflater
import com.example.gsport24.root.ext.show

class BottomNavigationView @JvmOverloads constructor(
	context : Context, attrs : AttributeSet? = null, defStyleAttr : Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

	private var previousSelectedPos = NavItem.None

	private val mBinding : LayoutBottomNavigationBinding = LayoutBottomNavigationBinding.inflate(context.inflater())
	private val navItems = mapOf(
		NavItem.Home to ItemModel(mBinding.navItem1Layout, mBinding.navItem1, mBinding.navItem1Shadow, context.getDrawableCompat(R.drawable.ic_home)),
		NavItem.Categories to ItemModel(mBinding.navItem2Layout, mBinding.navItem2, mBinding.navItem2Shadow, context.getDrawableCompat(R.drawable.ic_category)),
		NavItem.Notifications to ItemModel(mBinding.navItem3Layout, mBinding.navItem3, mBinding.navItem3Shadow, context.getDrawableCompat(R.drawable.ic_notifications)),
		NavItem.Profile to ItemModel(mBinding.navItem4Layout, mBinding.navItem4, mBinding.navItem4Shadow, context.getDrawableCompat(R.drawable.ic_profile)),
		NavItem.Menu to ItemModel(mBinding.navItem5Layout, mBinding.navItem5, mBinding.navItem5Shadow, context.getDrawableCompat(R.drawable.ic_menu)),
	)
	private val _callbacks : ArrayList<ItemCallback> = ArrayList()

	init {
		clipChildren = false
		clipToPadding = false
		addView(mBinding.root, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))

		navItems.forEach { entry ->
			val source = entry.value.drawable?.toBitmap()
			if (entry.value.blurredImage == null) {
				entry.value.blurredImage = getBlurred(source!!)
			}
			entry.value.layout.setOnClickListener {
				if (entry.key != previousSelectedPos) {
					if (previousSelectedPos != NavItem.None)
						unCheckItem(previousSelectedPos)
					checkItem(entry.key)
					_callbacks.forEach {
						it.onItemSelected(entry.key)
					}
				} else {
					_callbacks.forEach {
						it.onItemReSelected(entry.key)
					}
				}
			}
		}
	}

	fun addItemCallback(callback : ItemCallback) {
		_callbacks.add(callback)
	}

	fun checkItem(navItem : NavItem) {
		navItems[navItem]?.image?.setColorFilter(context.getColorCompat(R.color.colorAccent))
		navItems[navItem]?.blur?.show()
		previousSelectedPos = navItem
	}

	private fun unCheckItem(navItem : NavItem) {
		navItems[navItem]?.image?.setColorFilter(context.getColorCompat(R.color.tint_disabled_gray))
		navItems[navItem]?.blur?.hide()
	}

	private data class ItemModel(
		val layout : View,
		val image : AppCompatImageView,
		val blur : AppCompatImageView,
		val drawable : Drawable?
	) {

		var blurredImage : Bitmap? = null
			set(value) {
				field = value
				blur.setImageBitmap(value)
			}

		init {
			blur.hide()
		}
	}

	private fun getBlurred(source : Bitmap) : Bitmap {

		val blurredBitmap : Bitmap = Bitmap.createBitmap(source)

		val renderScript = RenderScript.create(context)

		val input = Allocation.createFromBitmap(
			renderScript,
			source,
			Allocation.MipmapControl.MIPMAP_FULL,
			Allocation.USAGE_SCRIPT
		)

		val output = Allocation.createTyped(renderScript, input.type)

		val script = ScriptIntrinsicBlur.create(
			renderScript,
			Element.U8_4(renderScript)
		)

		script.setInput(input)
		script.setRadius(4f)

		script.forEach(output)
		output.copyTo(blurredBitmap)

		source.recycle()
		return blurredBitmap
	}

	interface ItemCallback {

		fun onItemSelected(item : NavItem)
		fun onItemReSelected(item : NavItem)

	}

	enum class NavItem {
		Home,
		Categories,
		Notifications,
		Profile,
		Menu,
		None;

	}

}