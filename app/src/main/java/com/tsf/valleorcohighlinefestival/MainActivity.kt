package com.tsf.valleorcohighlinefestival

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tsf.valleorcohighlinefestival.ui.theme.ValleOrcoHighlineFestivalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValleOrcoHighlineFestivalTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "home") {
                    composable("home") { HomeScreen(navController) }
                    composable("events") { EventsScreen() }
                    composable("lines") { LinesScreen() }
                    composable("contacts") { ContactsScreen() }
                    composable("map") { MapScreen() }
                }

            }
        }
    }
}
