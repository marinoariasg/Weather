package com.marinoariasg.conduentweather.screens.searchWeather


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.marinoariasg.conduentweather.R
import com.marinoariasg.conduentweather.databinding.FragmentSearchWeatherBinding

/**
 * A simple [Fragment] subclass.
 */
class SearchWeatherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentSearchWeatherBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search_weather, container, false
        )

        // Reference of the application that this fragment is attached to, pass it to the
        // viewModelFactory provider
        val application = requireNotNull(this.activity).application

        // TODO: get the unitsFormat from SharedPreferences
        //Create an instance of the ViewModelFactory
        val searchWeatherViewModelFactory =
            SearchWeatherViewModelFactory(unitsFormat = "metric", application = application)

        // Get the ViewModel associated with this fragment.
        val searchWeatherViewModel = ViewModelProviders.of(
            this, searchWeatherViewModelFactory
        ).get(SearchWeatherViewModel::class.java)

        // TODO: Change for snack bar
        searchWeatherViewModel.userInputFeedBack.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })

        // Give the binding access to the searchLocationViewModel
        binding.searchWeatherViewModel = searchWeatherViewModel

        binding.lifecycleOwner = this

        return binding.root
    }
}
