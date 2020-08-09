package com.example.testdemohyperlink.models

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id")
    val id: Int,
    @SerializedName("large_url")
    val large_url: String,
    @SerializedName("source_id")
    val source_id: String,
    @SerializedName("url")
    val url: String
)