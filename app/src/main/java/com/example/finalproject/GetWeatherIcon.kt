package com.example.finalproject

fun GetIconCode(iconCode: String?): Int {
    return when (iconCode) {
        "01d", "01n", "800" -> R.drawable.clear
        "02d", "02n", "801" -> R.drawable.fewclouds
        "03d", "03n", "802" -> R.drawable.scatteredclouds
        "04d", "04n", "803", "804" -> R.drawable.brokenclouds
        "09d", "09n", "300", "301", "302", "310", "311", "312", "313", "314", "321" -> R.drawable.showerrain
        "10d", "10n", "500", "501", "502", "503", "504", "520", "521", "522", "531", "511" -> R.drawable.rain
        "11d", "11n", "200", "201", "202", "210", "211", "212", "230", "231", "232", "221" -> R.drawable.thunderstorm
        "13d", "13n", "600", "601", "602", "611", "612", "613", "615", "616", "620", "621", "622" -> R.drawable.snow
        "50d", "50n", "701", "711", "721", "731", "741", "751", "761", "762", "771", "781" -> R.drawable.mist
        else -> R.drawable.clear
    }
}
