package com.marinoariasg.conduentweather.screens

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

/**
 * These functions create a formatted string that can be set in a TextView.
 */


fun formatTemperatureToString(temperature: Double?): String {
    return "${temperature?.toInt() ?: ""}°"
}


/**
 * Take the Long milliseconds unix, UTC,
 * and convert it to a nicely formatted string for display.
 *
 * EEEE - Display the long letter version of the weekday
 * MMM - Display the letter abbreviation of the month
 * dd-yyyy - day in month and full year numerically
 * HH:mm - Hours and minutes in 24hr format
 */
@SuppressLint("SimpleDateFormat")
fun convertLongToDateString(unixTime: Long?): String {
    val sdf = SimpleDateFormat("MMM-dd-yyyy HH:mm")
    return if (unixTime != null) {
        val date = unixTime.times(1000).let { java.util.Date(it) }
        sdf.format(date)
    } else ""
}

@SuppressLint("SimpleDateFormat")
fun convertLongToDateStringJustTime(unixTime: Long?): String {
    val sdf = SimpleDateFormat("h:mm a")
    return if (unixTime != null) {
        val date = unixTime.times(1000).let { java.util.Date(it) }
        sdf.format(date)
    } else ""
}