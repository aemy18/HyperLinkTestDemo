package com.example.testdemohyperlink.models

import com.google.gson.annotations.SerializedName

data class ImagesListResponse(
    @SerializedName("images")
    val images: List<Image>
)