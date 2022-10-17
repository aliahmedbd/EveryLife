package com.aliahmed.everylife.model

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    val success : Boolean?= true,
    val message : String?,
    @SerializedName("events") val events : List<Events>?
)