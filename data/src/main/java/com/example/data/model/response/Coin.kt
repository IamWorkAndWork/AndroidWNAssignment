package com.example.data.model.response


import com.google.gson.annotations.SerializedName

data class Coin(
    @SerializedName("approvedSupply")
    val approvedSupply: Boolean? = null,
    @SerializedName("color")
    val color: String? = null,
    @SerializedName("confirmedSupply")
    val confirmedSupply: Boolean? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("history")
    val history: List<String>? = null,
    @SerializedName("iconType")
    val iconType: String? = null,
    @SerializedName("iconUrl")
    val iconUrl: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("penalty")
    val penalty: Boolean? = null,
    @SerializedName("price")
    val price: String? = null,
    @SerializedName("rank")
    val rank: Int? = null,
    @SerializedName("slug")
    val slug: String? = null,
    @SerializedName("symbol")
    val symbol: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("uuid")
    val uuid: String? = null,
    @SerializedName("volume")
    val volume: Long? = null,
    @SerializedName("websiteUrl")
    val websiteUrl: String? = null
)