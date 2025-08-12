package com.tsf.valleorcohighlinefestival

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Valle Orco Highline Festival") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FeatureCard(
                title = "Programma Eventi",
                description = "Consulta il calendario completo",
                icon = Icons.Default.Event,
                onClick = { navController.navigate("events") }
            )
            FeatureCard(
                title = "Info Linee",
                description = "Dettagli su ogni highline",
                icon = Icons.Default.Terrain,
                onClick = { navController.navigate("lines") }
            )
            FeatureCard(
                title = "Contatti",
                description = "Numeri utili e social",
                icon = Icons.Default.Phone,
                onClick = { navController.navigate("contacts") }
            )
            FeatureCard(
                title = "Mappa Interattiva",
                description = "Visualizza aree e percorsi",
                icon = Icons.Default.Map,
                onClick = { navController.navigate("map") }
            )
        }
    }
}

@Composable
fun FeatureCard(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(40.dp)
            )
            Column {
                Text(text = title, style = MaterialTheme.typography.titleLarge)
                Text(text = description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
