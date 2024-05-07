package com.example.finalproject

import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherDisplay(
    temp: Double?,
    tempH: Double?,
    tempL: Double?,
    windSpeed: Double?,
    humidity : Int?,
    cloud : Int?,
    realFeel: Double?,
    weather: String?,
    icon: String?
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp, 9.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "${weather}", fontSize = 25.sp, fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = GetIconCode(icon)),
                contentDescription = "weather icon",
                modifier = Modifier.size(50.dp)
            )
        }
    }
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp, 9.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Temp: ${temp}°C", fontSize = 20.sp, fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier.weight(1f))
            Column {
                Text(text = "H: ${tempH}°C", fontSize = 15.sp, fontFamily = FontFamily.Monospace)
                Text(text = "L: ${tempL}°C", fontSize = 15.sp, fontFamily = FontFamily.Monospace)
            }
        }
    }
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp, 9.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 15.dp)
        ) {
            Text(text = "Wind Speed: ${windSpeed}m/s", fontSize = 13.sp, fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Humidity: ${humidity}%", fontSize = 13.sp, fontFamily = FontFamily.Monospace)
        }
    }
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp, 9.dp),
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 15.dp)
        ) {
            Text(text = "Clouds: ${cloud}%", fontSize = 14.sp, fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Feels Like: ${realFeel}°C", fontSize = 14.sp, fontFamily = FontFamily.Monospace)
        }
    }
}
