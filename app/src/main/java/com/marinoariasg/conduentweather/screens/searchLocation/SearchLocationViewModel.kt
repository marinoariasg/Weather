package com.marinoariasg.conduentweather.screens.searchLocation


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

class SearchLocationViewModel(private val _unitsFormat: String = "metric", application: Application) :
    AndroidViewModel(application) {

    private val _response = MutableLiveData<WeatherData>()

    // The external immutable LiveData, The xml binding is observing this val
    val response: LiveData<WeatherData>
        get() = _response

    private var vieModelJob = Job()
    private val viewModelScope = CoroutineScope(vieModelJob + Dispatchers.Main)

    private val weatherRepository: WeatherRepository = WeatherRepository()

    // By City Name
    val byCityName = SearchByCityName(units = _unitsFormat)
    // By City Id
    val byCityId = SearchByCityId(units = _unitsFormat)
    // By lat lon
    val byLatAndLon = SearchByLatAndLon(units = _unitsFormat)
    // By ZipCode
    val byZipCode = SearchByZipCode(units = _unitsFormat)

    // Button Visibility and options
    // TODO: Change for buttonVisibility
    private var _showButton = MutableLiveData<Int>(GONE)
    val showButton: LiveData<Int>
        get() = _showButton

    /** **/
    private fun buttonVisible() {
        _showButton.value = VISIBLE
    }

    fun onSearch() {
        viewModelScope.launch {
            when (VISIBLE) {
                byCityName.visibility.value -> _response.value = byCityName.getDataFromRepository(weatherRepository)
                byCityId.visibility.value -> _response.value = byCityId.getDataFromRepository(weatherRepository)
                byLatAndLon.visibility.value -> _response.value = byLatAndLon.getDataFromRepository(weatherRepository)
                byZipCode.visibility.value -> _response.value = byZipCode.getDataFromRepository(weatherRepository)
            }
        }
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


    override fun onCleared() {
        super.onCleared()
        vieModelJob.cancel()
    }


}