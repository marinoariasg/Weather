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
import com.marinoariasg.conduentweather.databinding.FragmentCurrentLocationBinding
import com.marinoariasg.conduentweather.stringLiveData

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

        // TODO: Add also phone's current location data
        var simpleWeatherData = SimpleWeatherData(cityName = DEFAULT_CITY)


        // Create an instance of the ViewModelFactory
        var currentLocationModelFactory = CurrentLocationModelFactory(simpleWeatherData)

        // Get the ViewModel associated with this fragment.
        var currentLocationViewModel = ViewModelProviders.of(
            this, currentLocationModelFactory
        ).get(CurrentLocationViewModel::class.java)

        // Get Location from SharedPreferences and listen for changes
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        preferences.stringLiveData("city_name", DEFAULT_CITY).observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            simpleWeatherData = SimpleWeatherData(cityName = it)
            currentLocationViewModel.simpleWeatherData = simpleWeatherData
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
            // TODO: Add when settings_fragment fragment is ready
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
