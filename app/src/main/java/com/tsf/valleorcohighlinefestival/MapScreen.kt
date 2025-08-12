package com.tsf.valleorcohighlinefestival

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen() {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        Configuration.getInstance().load(context, context.getSharedPreferences("osmdroid", 0))
    }

    AndroidView(factory = {
        MapView(it).apply {
            setMultiTouchControls(true)
            controller.setZoom(15.0)
            controller.setCenter(GeoPoint(45.4334, 7.2586))

            // Aggiungi marker
            val marker = Marker(this).apply {
                position = GeoPoint(45.4334, 7.2586)
                setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                title = "Ceresole - Punto Festival"
                setOnMarkerClickListener { _, _ ->
                    // qui puoi mostrare info o navigare a dettagli
                    true
                }
            }
            overlays.add(marker)

            // Overlay posizione utente
            val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), this)
            locationOverlay.enableMyLocation()
            overlays.add(locationOverlay)
        }
    }, modifier = Modifier.fillMaxSize())

    var hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted -> hasLocationPermission = granted }
    )

    val mapView = remember {
        MapView(context).apply {
            setMultiTouchControls(true)
            controller.setZoom(15.0)
            controller.setCenter(GeoPoint( 45.4334, 7.2586)) // Ceresole
        }
    }

    val pointsOfInterest = listOf(
        GeoPoint(45.4299, 7.2503) to "Rifugio Fonti Minerali",
        GeoPoint(45.4299, 7.2477) to "Midlines Area Fonti",
        GeoPoint(45.4360, 7.2691) to "Highlines Area Ice Park"
    )

    pointsOfInterest.forEach { (geoPoint, title) ->
        val marker = Marker(mapView).apply {
            position = geoPoint
            setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            this.title = title
            setOnMarkerClickListener { _, _ ->
                showInfoWindow() // mostra popup con titolo
                true
            }
        }
        mapView.overlays.add(marker)
    }

    if (hasLocationPermission) {
        val myLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), mapView)
        myLocationOverlay.enableMyLocation()
        mapView.overlays.add(myLocationOverlay)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                mapView.controller.setCenter(GeoPoint(45.4334, 7.2586))
                mapView.controller.setZoom(15.0)
            }) {
                Icon(Icons.Filled.MyLocation, contentDescription = "Ricentra")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (hasLocationPermission) {
                AndroidView(
                    factory = { mapView },
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("L'app ha bisogno del permesso di localizzazione per mostrare la mappa.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION) }) {
                        Text("Consenti permesso posizione")
                    }
                }
            }
        }
    }
}
