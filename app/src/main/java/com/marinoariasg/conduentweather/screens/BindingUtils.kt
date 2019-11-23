package com.marinoariasg.conduentweather.screens

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.marinoariasg.conduentweather.R
import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters.*
import timber.log.Timber

@BindingAdapter("temperature")
fun TextView.setTemperature(item: WeatherData?) {
    item?.let {
        text =
            formatTemperatureToString(
                item.main?.temperature
            )
    }
}

// TODO: Improve this, if the icon name change on weather Api then we need to update that.
@BindingAdapter("weatherIcon")
fun ImageView.setImage(item: WeatherData?) {
    item?.let {
        setImageResource(
            when (item.weather?.get(0)?.icon) {
                "01d" -> R.drawable.clear_sky_d
                "01n" -> R.drawable.clear_sky_n
                "02d" -> R.drawable.few_clouds_d
                "02n" -> R.drawable.few_clouds_n
                "03d" -> R.drawable.scatterd_clouds
                "03n" -> R.drawable.scatterd_clouds
                "04d" -> R.drawable.broken_clouds
                "04n" -> R.drawable.broken_clouds
                "09d" -> R.drawable.shower_rain
                "09n" -> R.drawable.shower_rain
                "10d" -> R.drawable.rain
                "10n" -> R.drawable.rain
                "11d" -> R.drawable.thunderstorm
                "11n" -> R.drawable.thunderstorm
                "13d" -> R.drawable.snow
                "13n" -> R.drawable.snow
                "50d" -> R.drawable.mist
                "50n" -> R.drawable.mist
                else -> R.drawable.missing
            }
        )
    }
}

@BindingAdapter("cityName")
fun TextView.setCityName(item: WeatherData?) {
    item?.let {
        text = item.cityName
    }
}

@BindingAdapter("timeOfCalculation")
fun TextView.setTimeOfCalculation(item: WeatherData?) {
    item?.let {
        text = convertLongToDateString(item.timeOfDataCalculation)
    }
}

@BindingAdapter("description")
fun TextView.setDescription(item: WeatherData?) {
    item?.let {
        text = item.weather?.get(0)?.description.toString()
    }
}

// TODO do the concatenation on [Utils] and do validation
@BindingAdapter("variousProperties")
fun TextView.setVariousProperties(item: WeatherData?) {
    item?.let {
        text = "Cloudiness:         ${item?.clouds?.cloudiness}%\n" +
                "Humidity:             ${item?.main?.humidity}%\n" +
                "Wind Speed: ${item.wind?.speed} mph\n" +
                "Sunrise:         ${convertLongToDateStringJustTime(
                    item.sys?.sunrise
                )}\n" +
                "Sunset:          ${convertLongToDateStringJustTime(
                    item.sys?.sunset
                )}"
    }
}

@BindingAdapter("firstInput")
fun TextInputEditText.setFirstInput(searchParameterToShow: Search?) {
    Timber.i("Binding first input to: $searchParameterToShow")
    setFirstInput(searchParameterToShow!!, this)
}
private fun setFirstInput(searchParameterToShow: Search, textInputEditText: TextInputEditText) {
    textInputEditText.apply {
        setText("")
        hint = searchParameterToShow.firstInputHint
        inputType = searchParameterToShow.firstInputType
        visibility = searchParameterToShow.firstInputVisibility
    }
}

@BindingAdapter("secondInput")
fun TextInputEditText.setSecondInput(searchParameterToShow: Search?) {
    Timber.i("Binding second input to: $searchParameterToShow")
    setSecondInput(searchParameterToShow!!, this)
}
private fun setSecondInput(searchParameterToShow: Search, textInputEditText: TextInputEditText) {
    textInputEditText.apply {
        setText("")
        hint = searchParameterToShow.secondInputHint
        inputType = searchParameterToShow.secondInputInputType
        visibility = searchParameterToShow.secondInputVisibility
    }
}
