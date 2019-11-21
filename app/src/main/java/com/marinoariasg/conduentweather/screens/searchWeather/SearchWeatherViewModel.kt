package com.marinoariasg.conduentweather.screens.searchWeather

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository
import com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchWeatherViewModel(_unitsFormat: String = "metric", application: Application) :
    AndroidViewModel(application) {

    private val _weatherResponse = MutableLiveData<WeatherData>()
    // The external immutable LiveData, The xml binding is observing this val
    val weatherResponse: LiveData<WeatherData>
        get() = _weatherResponse

    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val weatherRepository: WeatherRepository = WeatherRepository()

    private val searchingParameters = SearchingParameters(_unitsFormat)

    private var _searchParameterToShow = MutableLiveData<Search>()
    // Used by TextView on the xml
    val searchParameterToShow: LiveData<Search>
        get() = _searchParameterToShow

    // EditTexts inputs from the xml
    var editTextFirstInput = ""
    var editTextSecondInput = ""

    // Button Visibility and options
    private var _buttonVisibility = MutableLiveData<Int>(View.GONE)
    // Used by the xml
    val buttonVisibility: LiveData<Int>
        get() = _buttonVisibility

    fun onRadioButtonClicked(radioButtonId: Int) {
        when (radioButtonId) {
            1 -> setVisible(searchingParameters.byCityName)
            2 -> setVisible(searchingParameters.byCityId)
            3 -> setVisible(searchingParameters.byLatAndLon)
            4 -> setVisible(searchingParameters.byZipCode)
        }
    }

    private fun setVisible(searchParameter: Search) {
        setSearchButtonVisible()
        showThisSearchParameter(searchParameter)
    }

    private fun setSearchButtonVisible() {
        // Just do this if the button is not visible.
        if (buttonVisibility.value == View.GONE) _buttonVisibility.value = View.VISIBLE
    }

    // this is for the visibility of the editText
    private fun showThisSearchParameter(searchParameter: Search) {
        _searchParameterToShow.value = searchParameter
    }

    fun onButtonSearchClicked() {
        // Before checking for weather get the editText info from user
        searchingParameters.addInfoFromEtToShowingParameter(
            firstInput = editTextFirstInput, secondInput = editTextSecondInput,
            showingSearchParameter = searchParameterToShow.value!!
        )
        // Update with the current parameter selected by the radio button
        updateWeatherResponse(searchParameterToShow.value!!)
    }

    private fun updateWeatherResponse(searchParameter: Search) {
        viewModelScope.launch {
            _weatherResponse.value = searchParameter.getDataFromRepository(weatherRepository)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}