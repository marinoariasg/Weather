package com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters

import android.text.InputType
import android.view.View
import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository
import timber.log.Timber

const val HINT_CITY_ID = "City id"
const val HINT_CITY_NAME = "City Name"
const val HINT_COUNTY_CODE = "Country Code"
const val HINT_LAT = "Latitude"
const val HINT_LON = "Longitude"
const val HINT_ZIP_CODE = "City ZipCode"

/**
 * Sealed class that holds all the possibles parameters objects for search.
 * We set as sealed as these are a limited set of functionalities
 */
sealed class Search(val units: String) {

    abstract val parameterId: ParameterId

    abstract val firstInputHint: String
    abstract val firstInputType: Int
    abstract val firstInputVisibility: Int

    abstract val secondInputHint: String
    abstract val secondInputInputType: Int
    abstract val secondInputVisibility: Int

    abstract suspend fun getDataFromRepository(repository: WeatherRepository): WeatherData

    // TODO: Try to get a different return value, something that tells
    //  positive output yes it can be update
    //  negative output not this can not be done because of this: Negative output
    abstract fun canUpdateTextInputs(firstInput: String, secondInput: String): String?


    /** Search by City Id parameter */
    class ByCityId(units: String) : Search(units) {

        private var cityId: String = ""

        override val parameterId: ParameterId
            get() = ParameterId.CITY_ID

        override val firstInputHint
            get() = HINT_CITY_ID
        override val firstInputType: Int
            get() = InputType.TYPE_CLASS_NUMBER
        override val firstInputVisibility: Int
            get() = View.VISIBLE

        // Second parameter is not in use
        override val secondInputHint: String
            get() = ""
        override val secondInputInputType: Int
            get() = InputType.TYPE_CLASS_TEXT
        override val secondInputVisibility: Int
            get() = View.GONE

        override suspend fun getDataFromRepository(repository: WeatherRepository): WeatherData {
            Timber.i("Call repository")
            return repository.weatherByCityId(cityId = cityId, units = units)
        }

        // TODO: Fix validations for all possible conditions.
        override fun canUpdateTextInputs(firstInput: String, secondInput: String): String? {
            return if (firstInput.isNotEmpty()) {
                cityId = firstInput
                null
            } else {
                "e.g: City Id= 2172797"
            }
        }

    }

    /** Search by City name parameter */
    class ByCityName(units: String) : Search(units) {

        private var cityName: String = ""
        private var countryCode: String = ""

        override val parameterId: ParameterId
            get() = ParameterId.CITY_NAME

        override val firstInputHint
            get() = HINT_CITY_NAME
        override val firstInputType: Int
            get() = InputType.TYPE_CLASS_TEXT
        override val firstInputVisibility: Int
            get() = View.VISIBLE

        override val secondInputHint: String
            get() = HINT_COUNTY_CODE
        override val secondInputInputType: Int
            get() = InputType.TYPE_CLASS_TEXT
        override val secondInputVisibility: Int
            get() = View.VISIBLE

        override suspend fun getDataFromRepository(repository: WeatherRepository): WeatherData {
            Timber.i("Call repository")
            return repository.weatherByCityName(
                cityName = cityName, countryCode = countryCode, units = units
            )
        }

        // TODO: Fix validations for all possible conditions.
        override fun canUpdateTextInputs(firstInput: String, secondInput: String): String? {
            return if (firstInput.isNotEmpty()) {
                cityName = firstInput
                countryCode = secondInput
                null
            } else {
                "e.g: " +
                        "\nCityName= London" +
                        "\nor" +
                        "\nCityName= London; CountryCode= Uk "
            }
        }

    }

    /** Search by City Latitude and Longitude parameter */
    class ByLatAndLon(units: String) : Search(units) {

        private var latitude: String = ""

        private var longitude: String = ""

        override val parameterId: ParameterId
            get() = ParameterId.LAT_LON

        override val firstInputHint
            get() = HINT_LAT
        override val firstInputType: Int
            get() = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED
        override val firstInputVisibility: Int
            get() = View.VISIBLE

        override val secondInputHint: String
            get() = HINT_LON
        override val secondInputInputType: Int
            get() = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED
        override val secondInputVisibility: Int
            get() = View.VISIBLE

        override suspend fun getDataFromRepository(repository: WeatherRepository): WeatherData {
            Timber.i("Call repository")
            return repository.weatherByLatAndLon(lat = latitude, lon = longitude, units = units)
        }

        // TODO: Fix validations for all possible conditions.
        override fun canUpdateTextInputs(firstInput: String, secondInput: String): String? {
            return if (firstInput.isNotEmpty()) {
                latitude = firstInput
                longitude = secondInput
                null
            } else {
                "e.g: lat= 4.72069, lon= -73.96926"
            }
        }

    }

    /** Search by City Zip code parameter */
    class ByZipCode(units: String) : Search(units) {

        private var zipCode: String = ""
        private var countryCode: String = ""

        override val parameterId: ParameterId
            get() = ParameterId.ZIP_CODE

        override val firstInputHint
            get() = HINT_ZIP_CODE
        override val firstInputType: Int
            get() = InputType.TYPE_CLASS_TEXT
        override val firstInputVisibility: Int
            get() = View.VISIBLE

        override val secondInputHint: String
            get() = HINT_COUNTY_CODE
        override val secondInputInputType: Int
            get() = InputType.TYPE_CLASS_TEXT
        override val secondInputVisibility: Int
            get() = View.VISIBLE

        override suspend fun getDataFromRepository(repository: WeatherRepository): WeatherData {
            Timber.i("Call repository")
            return repository.weatherByZipCode(
                zipCode = zipCode, countryCode = countryCode, units = units
            )
        }

        // TODO: Fix validations for all possible conditions.
        override fun canUpdateTextInputs(firstInput: String, secondInput: String): String? {
            return if (firstInput.isNotEmpty()) {
                zipCode = firstInput
                countryCode = secondInput
                null
            } else {
                "e.g: zipCode= 94040, CountryCode= us"
            }
        }
    }

}