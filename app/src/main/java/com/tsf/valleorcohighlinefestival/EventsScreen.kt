package com.tsf.valleorcohighlinefestival

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


val sampleEvents = listOf(
    Event(1, "Apertura Festival", "Cerimonia di apertura sul palco principale", "10:00", "Venerdì"),
    Event(2, "Workshop Highline", "Tecniche di sicurezza e ancoraggi", "12:30", "Venerdì"),
    Event(3, "Demo Pro", "Atleti professionisti sulla linea lunga", "15:00", "Sabato"),
    Event(4, "Concerto serale", "Live music con band locale", "21:00", "Sabato"),
    Event(5, "Chiusura Festival", "Ringraziamenti e saluti", "18:00", "Domenica")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Programma Eventi") }) }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(sampleEvents) { event ->
                EventCard(event = event)
            }
        }
    }
}

@Composable
fun EventCard(event: Event) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        onClick = {
            // TODO: vai al dettaglio evento
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "${event.day} - ${event.time}", style = MaterialTheme.typography.labelMedium)
            Text(text = event.title, style = MaterialTheme.typography.titleLarge)
            Text(text = event.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
