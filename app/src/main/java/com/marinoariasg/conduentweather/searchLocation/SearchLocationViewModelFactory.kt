package com.marinoariasg.conduentweather.searchLocation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class SearchLocationViewModelFactory(private val unitsFormat: String) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchLocationViewModel::class.java)) {
            return SearchLocationViewModel(unitsFormat) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}