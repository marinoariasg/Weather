package com.marinoariasg.conduentweather.screens.searchWeather


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

const val GONE: Int = 8
const val VISIBLE: Int = 0

class SearchWeatherViewModel(private val _unitsFormat: String = "metric", application: Application) :
    AndroidViewModel(application) {

    private val _weatherResponse = MutableLiveData<WeatherData>()

    // The external immutable LiveData, The xml binding is observing this val
    val weatherResponse: LiveData<WeatherData>
        get() = _weatherResponse

    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val weatherRepository: WeatherRepository = WeatherRepository()

    val byCityName = SearchByCityName(units = _unitsFormat)

    val byCityId = SearchByCityId(units = _unitsFormat)

    val byLatAndLon = SearchByLatAndLon(units = _unitsFormat)

    val byZipCode = SearchByZipCode(units = _unitsFormat)

    // Button Visibility and options
    private var _buttonVisibility = MutableLiveData<Int>(GONE)
    val buttonVisibility: LiveData<Int>
        get() = _buttonVisibility

    private fun buttonVisible() {
        _buttonVisibility.value = VISIBLE
    }

    /** Radio buttons options **/
    fun onSetByCityName() {
        Timber.i("Radio button: Search by City name")
        byCityName.visible()
        byCityId.gone()
        byLatAndLon.gone()
        byZipCode.gone()
        buttonVisible()
    }

    fun onSetByCityId() {
        Timber.i("Radio button: Search by City ID")
        byCityName.gone()
        byLatAndLon.gone()
        byZipCode.gone()
        byCityId.visible()
        buttonVisible()
    }

    fun onSetByLatAndLon() {
        Timber.i("Radio button: Search by lat, lon")
        byCityName.gone()
        byCityId.gone()
        byZipCode.gone()
        byLatAndLon.visible()
        buttonVisible()
    }

    fun onSetByZipCode() {
        Timber.i("Radio button: Search by ZipCode")
        byCityName.gone()
        byCityId.gone()
        byLatAndLon.gone()
        byZipCode.visible()
        buttonVisible()
    }

    // Button can only call this when is visible (after a radio button have been selected)
    fun onSearch() {
        viewModelScope.launch {
            when (VISIBLE) {
                byCityName.visibility.value -> updateWeatherResponse(byCityName)
                byCityId.visibility.value -> updateWeatherResponse(byCityId)
                byLatAndLon.visibility.value -> updateWeatherResponse(byLatAndLon)
                byZipCode.visibility.value -> updateWeatherResponse(byZipCode)
            }
        }
    }

    // Polymorphism ;)
    private suspend fun updateWeatherResponse(searchObject: Search){
        _weatherResponse.value = searchObject.getDataFromRepository(weatherRepository)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}