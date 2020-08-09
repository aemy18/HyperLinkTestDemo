package com.example.testdemohyperlink.api

import com.example.testdemohyperlink.models.Image
import com.example.testdemohyperlink.models.ImagesListResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("latest")
    fun getImages(): Call<ImagesListResponse>
}