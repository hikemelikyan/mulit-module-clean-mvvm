package com.example.gsport24.shared

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.example.gsport24.R

class CircleShadowImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val shadowPaint: Paint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }
    private val path: Path by lazy { Path() }
    private val rectF: RectF by lazy { RectF() }
    private var roundedCorners: Int
    private var cornerRadius: Float
    private var roundType: RoundType?

    enum class RoundType {
        CIRCLE,
        ROUNDISH;

        companion object {
            fun from(value: Int): RoundType? {
                return values().find { it.ordinal == value }
            }
        }
    }

    enum class RoundedCorners(val value: Int) {
        None(0),
        TopLeft(1),
        TopRight(2),
        BottomRight(4),
        BottomLeft(8),
        All(15);
    }

    init {
        val incomingAttrs = context.obtainStyledAttributes(attrs, R.styleable.CircleShadowImageView)
        val imageType = incomingAttrs.getInt(R.styleable.CircleShadowImageView_roundStyle, -1)
        check(imageType != -1) { "Image roundStyle is not found" }
        roundType = RoundType.from(imageType)
        cornerRadius = if (roundType == RoundType.CIRCLE) {
            0f
        } else {
            incomingAttrs.getDimensionPixelSize(
                R.styleable.CircleShadowImageView_cornerRadius,
                0
            ).toFloat()
        }
        roundedCorners = incomingAttrs.getInt(
            R.styleable.CircleShadowImageView_roundedCorners,
            RoundedCorners.None.value
        )
        if (roundType != RoundType.CIRCLE) {
            check(roundedCorners != RoundedCorners.None.value) { "Rounded corners are not found, use AppCompatImageView instead" }
        }
        val shadowColor = incomingAttrs.getColor(
            R.styleable.CircleShadowImageView_shadowColor,
            ContextCompat.getColor(context, android.R.color.transparent)
        )
        val shadowRadius = incomingAttrs.getDimensionPixelSize(
            R.styleable.CircleShadowImageView_shadowRadius,
            1
        ).toFloat()
        incomingAttrs.recycle()
        shadowPaint.maskFilter = BlurMaskFilter(shadowRadius, BlurMaskFilter.Blur.NORMAL)
        shadowPaint.color = shadowColor
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        buildPath(w, h)
    }

    private fun buildPath(w: Int, h: Int) {
        path.rewind()
        if (cornerRadius == 0f && roundType == RoundType.CIRCLE) {
            check(w == h) { "Circle image android:width must be equal to android:height" }
            cornerRadius = (h / 2).toFloat()
            roundedCorners = RoundedCorners.All.value
        }
        rectF.set(-cornerRadius, -cornerRadius, cornerRadius, cornerRadius)
        path.moveTo(0f, 0f)

        if (RoundedCorners.TopLeft.isRounded()) {
            rectF.offsetTo(0f, 0f)
            path.arcTo(rectF, 180f, 90f)
        } else {
            path.moveTo(0f, 0f)
        }
        if (RoundedCorners.TopRight.isRounded()) {
            rectF.offsetTo(w - cornerRadius * 2, 0f)
            path.arcTo(rectF, 270f, 90f)
        } else {
            path.lineTo(w.toFloat(), 0f)
        }

        if (RoundedCorners.BottomRight.isRounded()) {
            rectF.offsetTo(w - cornerRadius * 2, h - cornerRadius * 2)
            path.arcTo(rectF, 0f, 90f)
        } else {
            path.lineTo(w.toFloat(), h.toFloat())
        }

        if (RoundedCorners.BottomLeft.isRounded()) {
            rectF.offsetTo(0f, h - cornerRadius * 2)
            path.arcTo(rectF, 90f, 90f)
        } else {
            path.lineTo(0f, h.toFloat())
        }

        path.close()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawPath(path, shadowPaint)
        if (!path.isEmpty) {
            canvas?.clipPath(path)
        }
        super.onDraw(canvas)
        super.setLayerPaint(paint)
    }

    private fun RoundedCorners.isRounded(): Boolean {
        return roundedCorners and value == value
    }

    fun setShadowColor(@ColorInt color: Int) {
        shadowPaint.color = color
        invalidate()
    }

    fun setShadowRadius(radius: Int) {
        shadowPaint.maskFilter = BlurMaskFilter(radius.toFloat(), BlurMaskFilter.Blur.OUTER)
        invalidate()
    }

    fun setRoundType(type: RoundType) {
        roundType = type
        invalidate()
    }

    fun setRoundedCorners(vararg corners: RoundedCorners) {
        roundedCorners = 0
        corners.forEach {
            roundedCorners = roundedCorners or it.value
        }
        invalidate()
    }

    fun setCornerRadius(radius: Float){
        cornerRadius = radius
        invalidate()
    }

}