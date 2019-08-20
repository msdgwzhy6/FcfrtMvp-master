package com.fcfrt.baselib.base

import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by jiang on 12/3/15.
 */
class FcfrtBaseViewHolder : RecyclerView.ViewHolder {




    constructor(itemView:View) : super(itemView) {

        /**
         * 设置水波纹背景
         */
        if (itemView.background == null) {
            val typedValue = TypedValue()
            val theme = itemView.context.theme
            val top = itemView.paddingTop
            val bottom = itemView.paddingBottom
            val left = itemView.paddingLeft
            val right = itemView.paddingRight
            if (theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)) {
                itemView.setBackgroundResource(typedValue.resourceId)
            }
            itemView.setPadding(left, top, right, bottom)
        }
    }
}
