package com.marinoariasg.conduentweather.searchLocation

import androidx.lifecycle.ViewModel
import timber.log.Timber
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.icu.lang.UCharacter.GraphemeClusterBreak.T





class SearchLocationViewModel (private val _unitsFormat: String) : ViewModel() {

    fun onCheckApiParameter() {
        Timber.i("Clicked")
    }


}