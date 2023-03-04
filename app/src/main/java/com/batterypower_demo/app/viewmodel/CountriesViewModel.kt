package com.batterypower_demo.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batterypower_demo.app.model.CountrieModel
import com.batterypower_demo.app.repo.CountriesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountriesViewModel (val countruRepo : CountriesRepo): ViewModel(){
    init {
        viewModelScope.launch (Dispatchers.IO) {
            countruRepo.getCountries()
        }

    }

    fun getCityFromCountry(country : String) {
        viewModelScope.launch (Dispatchers.IO) {
            countruRepo.getCity(country)
        }
    }

    val countries : LiveData<CountrieModel>
        get() = countruRepo.countries

    val city : LiveData<CountrieModel.Data>
        get() = countruRepo.city


}