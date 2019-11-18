package com.marinoariasg.conduentweather.currentLocation


import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import android.widget.Toast


import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.marinoariasg.conduentweather.R
import com.marinoariasg.conduentweather.WeatherLookUpParameters
import com.marinoariasg.conduentweather.databinding.FragmentCurrentLocationBinding
import com.marinoariasg.conduentweather.settingsFragment.stringLiveData
import timber.log.Timber

//TODO: add phone current location


/**
 * A simple [Fragment] subclass.
 */

const val DEFAULT_CITY = "Havant"

class CurrentLocationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCurrentLocationBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_current_location, container, false
        )

        val weatherLookUpParameters = WeatherLookUpParameters(cityName = DEFAULT_CITY)

        // Create an instance of the ViewModelFactory with default weather parameters
        val currentLocationModelFactory = CurrentLocationModelFactory(weatherLookUpParameters)

        // Get the ViewModel associated with this fragment.
        val currentLocationViewModel = ViewModelProviders.of(
            this, currentLocationModelFactory
        ).get(CurrentLocationViewModel::class.java)

        // Get City name or Country from SharedPreferences and listen for changes on this preference
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        preferences.stringLiveData("city_name", DEFAULT_CITY).observe(this,
            Observer { cityName ->
                Timber.i("Place changed on preferences: $cityName")
                // update the parameters with the new changed data before calling the weather API
                currentLocationViewModel.weatherLookUpParameters =
                    WeatherLookUpParameters(cityName = cityName)
                // TODO: Is is calling twice this function ensure that this gets call only once
                // Call the weather API from ViewModel.
                currentLocationViewModel.getWeatherDataByCityName()
            })

        // Give the binding access to the currentLocationViewModel
        binding.currentLocationViewModel = currentLocationViewModel

        binding.lifecycleOwner = this

        setHasOptionsMenu(true)

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.current_location_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settingsFragment -> NavigationUI.onNavDestinationSelected(
                item, view!!.findNavController()
            )
            R.id.search_location_fragment -> NavigationUI.onNavDestinationSelected(
                item, view!!.findNavController()
            )
        }
        return super.onOptionsItemSelected(item)
    }

}
