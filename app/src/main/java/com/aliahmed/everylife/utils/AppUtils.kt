package com.aliahmed.everylife.utils

import android.app.Activity
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.aliahmed.everylife.R
import com.google.android.material.snackbar.Snackbar
import java.util.*

fun String.getIcon(): Int {
    when (this.uppercase(Locale.getDefault())) {
        Types.GENERAL.name -> {
            return R.mipmap.general
        }
        Types.HYDRATION.name -> {
            return R.mipmap.hydration
        }
        Types.NUTRITION.name -> {
            return R.mipmap.nutrition
        }
        Types.MEDICATION.name -> {
            return R.mipmap.medication
        }
        else -> {
            return R.mipmap.general
        }
    }
}

fun showSnackBar(
    activity: Activity, message: String, action: String? = null,
    actionListener: View.OnClickListener? = null, duration: Int = Snackbar.LENGTH_SHORT, color: Int
) {
    val snackBar = Snackbar.make(activity.findViewById(android.R.id.content), message, duration)
        .setBackgroundTint(color)
        .setTextColor(Color.WHITE)
    val view = snackBar.view
    val params = view.layoutParams as FrameLayout.LayoutParams
    params.gravity = Gravity.TOP
    view.layoutParams = params
    if (action != null && actionListener != null) {
        snackBar.setAction(action, actionListener)
    }
    snackBar.show()
}