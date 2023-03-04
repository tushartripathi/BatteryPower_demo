package com.batterypower_demo.app.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.batterypower_demo.app.api.ApiInterface
import com.batterypower_demo.app.model.CountrieModel

class CountriesRepo(private val apiInterface: ApiInterface) {

    private val courtriesLiveData = MutableLiveData<CountrieModel>();
    private val cityLiveData = MutableLiveData<CountrieModel.Data>();

    val countries : LiveData<CountrieModel>
        get() = courtriesLiveData

    val city : LiveData<CountrieModel.Data>
        get() = cityLiveData

    suspend fun getCountries() {
        val response = apiInterface.getCountries()
        if (response.isSuccessful) {
            courtriesLiveData.postValue(response.body())
        }
    }
    suspend fun getCity(country : String) {
        courtriesLiveData.value?.data?.forEach {
            if(it?.country == country)
            {
                cityLiveData.postValue(it)
            }
        }


    }



}