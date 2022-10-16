package com.aliahmed.everylife.model

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("events") val events : List<Events>
)