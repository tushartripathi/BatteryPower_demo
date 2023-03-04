package com.batterypower_demo.app.api

import com.batterypower_demo.app.model.CountrieModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("countries")
    suspend fun getCountries(): Response<CountrieModel>
}