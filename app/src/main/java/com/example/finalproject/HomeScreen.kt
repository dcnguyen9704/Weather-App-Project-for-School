package com.example.finalproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.getSystemService
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("MissingPermission")
@Composable

fun HomeScreen(navController: NavController, viewModel: LocationViewModel) {
    val context = LocalContext.current
    val locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager
    val locationPermissionState = rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION)

    if (locationPermissionState.status.isGranted) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.welcome),
                fontSize = 23.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(40.dp)
            )
            Divider(
                thickness = 2.dp,
                color = Color.Black,
                modifier = Modifier.padding(25.dp, 0.dp)
            )

            Spacer(modifier = Modifier.weight(0.2f))

            Text(
                text = stringResource(R.string.permission_granted),
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.padding(30.dp)
            )
            val location = locationManager.getLastKnownLocation(LocationManager.FUSED_PROVIDER)
            if (location != null) {
                viewModel.setLocation(longitude = location.longitude, latitude = location.latitude)
            }

            Spacer(modifier = Modifier.weight(0.2f))

            Button(
                onClick = { navController.navigate("currentWeather?lat=${viewModel.latitude}&lon=${viewModel.longitude}")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                ) {
                Text(
                    text = stringResource(R.string.current_weather),
                    fontSize = 15.sp,
                    fontFamily = FontFamily.Monospace
                    )
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.welcome),
                fontSize = 23.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(40.dp)
            )
            Divider(
                thickness = 2.dp,
                color = Color.Black,
                modifier = Modifier.padding(25.dp, 0.dp)
            )
            Spacer(modifier = Modifier.weight(0.2f))
            Image(
                painter = painterResource(id = R.drawable.warning),
                contentDescription = "warning sign",
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = stringResource(R.string.this_app_requires_location_services),
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.padding(30.dp)
            )
            Spacer(modifier = Modifier.weight(0.2f))
            Button(
                onClick = {locationPermissionState.launchPermissionRequest()},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp)
                ) {
                Text(
                    text = stringResource(R.string.give_location_permission),
                    fontSize = 15.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
        }
    }
}

