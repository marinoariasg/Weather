package com.marinoariasg.conduentweather.screens.searchLocation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.marinoariasg.conduentweather.R
import com.marinoariasg.conduentweather.databinding.FragmentSearchLocationBinding
import kotlinx.android.synthetic.main.fragment_search_location.*

/**
 * A simple [Fragment] subclass.
 */
class SearchLocationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentSearchLocationBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search_location, container, false
        )

        // Reference of the application that this fragment is attached to, pass it to the
        // viewModelFactory provider
        val application = requireNotNull(this.activity).application

        // TODO: get the unitsFormat from SharedPreferences
        //Create an instance of the ViewModelFactory
        val searchLocationViewModelFactory =
            SearchLocationViewModelFactory(
                unitsFormat = "metric", application = application
            )

        // Get the ViewModel associated with this fragment.
        val searchLocationViewModel = ViewModelProviders.of(
            this, searchLocationViewModelFactory
        ).get(SearchLocationViewModel::class.java)

        // Give the binding access to the searchLocationViewModel
        binding.searchLocationViewModel = searchLocationViewModel

        binding.lifecycleOwner = this

        return binding.root
    }
}
