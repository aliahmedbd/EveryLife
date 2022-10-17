package com.aliahmed.everylife.utils

import com.aliahmed.everylife.R
import com.aliahmed.everylife.network.ResponseModel
import java.lang.reflect.Type

fun String.getIcon(): Int {
    when (this) {
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