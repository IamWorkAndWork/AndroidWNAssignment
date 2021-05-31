package com.example.data.model.response


import com.google.gson.annotations.SerializedName

data class CoinResponse(
    @SerializedName("data")
    val `data`: Data? = null,
    @SerializedName("status")
    val status: String? = null
)