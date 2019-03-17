package com.bytebuilding.kudagoturkin.ui.custom

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.bytebuilding.kudagoturkin.R
import com.bytebuilding.kudagoturkin.utils.getString


class EventInfoView(
        context: Context,
        attrs: AttributeSet? = null
) : LinearLayoutCompat(context, attrs) {

    /**
     * Views
     * */
    private lateinit var mIcon: AppCompatImageView
    private lateinit var mDescription: AppCompatTextView

    /**
     * View state
     * */
    private var mIconRes: Int
    private var mDescriptionText: String

    init {
        inflate(context, R.layout.view_custom_event_info, this)

        orientation = HORIZONTAL

        mIconRes = DEFAULT_ICON_RES
        mDescriptionText = context.getString(R.string.stub)

        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.EventInfoView, 0, 0)

            mIconRes = typedArray.getResourceId(R.styleable.EventInfoView_eiv_icon, mIconRes)

            mDescriptionText = typedArray.getString(R.styleable.EventInfoView_eiv_description, mDescriptionText)

            typedArray.recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        mIcon = findViewById(R.id.icon)
        if (mIconRes != DEFAULT_ICON_RES) mIcon.setImageResource(mIconRes)

        mDescription = findViewById(R.id.description)
        mDescription.text = mDescriptionText
    }

    companion object {
        const val TAG = "EventInfoView"

        const val DEFAULT_ICON_RES = -1
    }

}