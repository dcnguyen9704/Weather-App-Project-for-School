package com.example.finalproject

import android.app.FragmentManager.BackStackEntry
import android.telecom.Call
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavBackStackEntry
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitInstance {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }
    val apiService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

interface ApiService {
    @GET("weather?")
    suspend fun fetchWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String = "1ad565f600855fd672b79df3b8935001",
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "en"
    ): WeatherData
}

@Composable
fun CurrentWeather(navController: NavHostController, viewModel: LocationViewModel) {
    var weatherData by remember { mutableStateOf<WeatherData?>(null) }


    LaunchedEffect(Unit) {
        val args = navController.previousBackStackEntry?.arguments
        val lat = args?.getDouble("lat") ?: 0.0
        val lon = args?.getDouble("lon") ?: 0.0

        try {
            weatherData = RetrofitInstance.apiService.fetchWeatherData(
                lat = viewModel.latitude,
                lon = viewModel.longitude,
                lang = "en"
            )
        } catch (e: Exception) {
            Log.e("CurrentWeatherScreen", "Error fetching weather data: ${e.message}")
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.current_weather),
            fontSize = 38.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(30.dp, 40.dp)
        )

        Divider(
            thickness = 2.dp,
            color = Color.Black,
            modifier = Modifier.padding(25.dp, 0.dp)
        )

        Spacer(modifier = Modifier.weight(0.2f))

        WeatherDisplay(
            temp = weatherData?.main?.temp,
            tempH = weatherData?.main?.temp_max,
            tempL = weatherData?.main?.temp_min,
            windSpeed = weatherData?.wind?.speed,
            humidity = weatherData?.main?.humidity,
            cloud = weatherData?.clouds?.all,
            realFeel = weatherData?.main?.feels_like,
            weather = weatherData?.weather?.get(0)?.description,
            icon = weatherData?.weather?.get(0)?.icon
        )

        Spacer(modifier = Modifier.weight(0.2f))

        Button(
            onClick = {navController.navigate("home")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp)
        ) {
            Text(
                text = stringResource(R.string.go_back_home),
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}