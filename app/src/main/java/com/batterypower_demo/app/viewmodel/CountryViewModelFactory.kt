package com.batterypower_demo.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.batterypower_demo.app.repo.CountriesRepo

class CountryViewModelFactory(private val countryRepo : CountriesRepo) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return CountriesViewModel(countryRepo) as T
    }
}