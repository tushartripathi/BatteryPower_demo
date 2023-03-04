package com.batterypower_demo.app.model


import androidx.annotation.Keep

@Keep
data class CountrieModel(
    val data: List<Data?>?,
    val error: Boolean?,
    val msg: String?
) {
    @Keep
    data class Data(
        val cities: List<String?>?,
        val country: String?,
        val iso2: String?,
        val iso3: String?
    )
}