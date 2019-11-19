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

class SearchLocationViewModel(private val _unitsFormat: String, application: Application) :
    AndroidViewModel(application) {

    private val _response = MutableLiveData<WeatherData>()

    // The external immutable LiveData, The xml binding is observing this val
    val response: LiveData<WeatherData>
        get() = _response

    private var vieModelJob = Job()
    private val viewModelScope = CoroutineScope(vieModelJob + Dispatchers.Main)

    private val weatherRepository: WeatherRepository = WeatherRepository()

    // TODO: Validations

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
        when (VISIBLE) {
            _showByCityNameOptions.value -> getDataByCityName()
            _showByCityIdOptions.value -> getDataByCityId()
            _showByLatAndLonOptions.value -> getDataByLatAndLon()
        }

    }

    private fun getDataByCityName() {
        Timber.i("By City name")
        viewModelScope.launch {
            _response.value = weatherRepository.cityNameData(
                cityName = cityName,
                countryCode = countryCode,
                units = "metric"
            )
        }
    }

    private fun getDataByCityId() {
        // Ensure that text is not empty
        if (cityId != "") {
            Timber.i("By City Id")
            viewModelScope.launch {
                _response.value = weatherRepository.cityByIdData(
                    cityId = cityId.toInt(), units = "metric"
                )
            }
        }
    }

    private fun getDataByLatAndLon() {
        Timber.i("By City lat and lon")
        // Ensure that text is not empty
        if (lat != "" && lon != "") {
            viewModelScope.launch {
                _response.value = weatherRepository.cityByLatAndLon(
                    lat = lat.toDouble(), lon = lon.toDouble(), units = "metric"
                )
            }
        }
    }

    // By City Name
    var cityName: String = ""
    var countryCode: String = ""
    private var _showByCityNameOptions = MutableLiveData<Int>(GONE)
    val showByCityNameOptions: LiveData<Int>
        get() = _showByCityNameOptions

    private fun byCityNameOptionsVisible() {
        _showByCityNameOptions.value =
            VISIBLE
    }

    private fun byCityNameOptionsGone() {
        _showByCityNameOptions.value = GONE
    }

    // By City Id
    var cityId: String = ""
    private var _showByCityIdOptions = MutableLiveData<Int>(GONE)
    val showByCityIdOptions: LiveData<Int>
        get() = _showByCityIdOptions

    private fun byCityIdOptionsVisible() {
        _showByCityIdOptions.value = VISIBLE
    }

    private fun byCityIdOptionsGone() {
        _showByCityIdOptions.value = GONE
    }

    // By lat lon
    var lat: String = ""
    var lon: String = ""
    // Internal
    private var _showByLatAndLonOptions = MutableLiveData<Int>(GONE)
    val showByLatAndLonOptions: LiveData<Int>
        get() = _showByLatAndLonOptions

    private fun byLatAndLonOptionsVisible() {
        _showByLatAndLonOptions.value = VISIBLE
    }

    private fun byLatAndLonOptionGone() {
        _showByLatAndLonOptions.value = GONE
    }


    /** Radio buttons options **/
    fun onSetByCityName() {
        Timber.i("By City name")
        byCityNameOptionsVisible()
        byCityIdOptionsGone()
        byLatAndLonOptionGone()
        buttonVisible()
    }

    fun onSetByCityId() {
        Timber.i("By City ID")
        byCityNameOptionsGone()
        byLatAndLonOptionGone()
        byCityIdOptionsVisible()
        buttonVisible()
    }

    fun onSetByLatAndLon() {
        Timber.i("By lat, lon")
        byCityNameOptionsGone()
        byCityIdOptionsGone()
        byLatAndLonOptionsVisible()
        buttonVisible()

    }

    fun onSetByZipCode() {
        Timber.i("By ZipCode")
    }


    override fun onCleared() {
        super.onCleared()
        vieModelJob.cancel()
    }


}