package com.marinoariasg.conduentweather.searchLocation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marinoariasg.conduentweather.R
import com.marinoariasg.conduentweather.databinding.FragmentSearchLocationBinding

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
            R.layout.fragment_search_location, container, false)

        return binding.root
    }


}
