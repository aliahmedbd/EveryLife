package com.aliahmed.everylife.utils

import com.aliahmed.everylife.R
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