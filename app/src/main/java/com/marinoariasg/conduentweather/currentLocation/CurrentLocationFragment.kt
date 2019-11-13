package com.marinoariasg.conduentweather.currentLocation


import android.os.Bundle
import android.view.*


import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.marinoariasg.conduentweather.R
import com.marinoariasg.conduentweather.databinding.FragmentCurrentLocationBinding


/**
 * A simple [Fragment] subclass.
 */
class CurrentLocationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCurrentLocationBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_current_location, container, false
        )

        // TODO: Change this with phone location
        val currentLocationData =
            CurrentLocationData(
                cityName = "Havant",
                countryCode = "uk"
            )

        // Create an instance of the ViewModelFactory
        val currentLocationModelFactory =
            CurrentLocationModelFactory(
                currentLocationData
            )

        // Get the ViewModel associated with this fragment.
        val currentLocationViewModel = ViewModelProviders.of(
            this, currentLocationModelFactory
        ).get(CurrentLocationViewModel::class.java)

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
            // TODO: Add when settings fragment is ready
            //R.id.settings -> settings_fragment
            R.id.search_location_fragment -> NavigationUI.onNavDestinationSelected(item, view!!.findNavController())
        }
        return super.onOptionsItemSelected(item)
    }

}
