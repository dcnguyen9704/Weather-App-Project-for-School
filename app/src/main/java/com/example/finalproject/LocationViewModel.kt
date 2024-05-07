package com.example.finalproject

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LocationViewModel: ViewModel() {
    var longitude by mutableStateOf<Double>(Double.NaN)
        private set

    var latitude by mutableStateOf<Double>(Double.NaN)
        private set

    fun setLocation(longitude: Double, latitude: Double) {
        this.longitude = longitude
        this.latitude = latitude
    }
}