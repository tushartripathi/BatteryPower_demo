package com.batterypower_demo.app.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiUtiliities {
    val BASE_URL = "https://countriesnow.space/api/v0.1/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}