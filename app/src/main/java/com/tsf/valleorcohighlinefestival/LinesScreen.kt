package com.tsf.valleorcohighlinefestival

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// ✅ Lista di linee di esempio
private val sampleLines = listOf(
    Line(1, "SkyBridge", 45, "Facile", "Zona camping"),
    Line(2, "Orco Line", 85, "Media", "Vicino falesia"),
    Line(3, "Long Valley", 120, "Difficile", "Valle centrale"),
    Line(4, "River Crossing", 60, "Media", "Fiume a sud"),
    Line(5, "Cliff Dancer", 100, "Difficile", "Canyon alto")
)

// ✅ Composable principale della schermata
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LinesScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Info Linee") }) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(sampleLines) { line ->
                LineCard(line)
            }
        }
    }
}

// ✅ Singola card per ogni linea
@Composable
fun LineCard(line: Line) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = line.name, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Lunghezza: ${line.length} m", style = MaterialTheme.typography.bodyMedium)
            Text("Difficoltà: ${line.difficulty}", style = MaterialTheme.typography.bodyMedium)
            Text("Posizione: ${line.location}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
