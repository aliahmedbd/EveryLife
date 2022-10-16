package com.aliahmed.everylife.model

import com.google.gson.annotations.SerializedName

data class Events(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("type") val type: String? = null
)
