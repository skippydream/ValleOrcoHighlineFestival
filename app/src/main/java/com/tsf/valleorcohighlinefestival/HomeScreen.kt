package com.tsf.valleorcohighlinefestival

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.LazyColumn
import java.io.File
import java.io.FileOutputStream
import androidx.core.content.FileProvider
import android.widget.Toast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current  // <-- QUI!

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Valle Orco Highline Festival") }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Text(
                    text = "WIP! Le informazioni inserite sono ancora casuali",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            item {
                FeatureCard(
                    title = "Programma Eventi",
                    description = "Consulta il calendario completo",
                    icon = Icons.Default.Event,
                    onClick = { navController.navigate("events") }
                )
            }
            item {
                FeatureCard(
                    title = "Info Linee",
                    description = "Dettagli su ogni highline",
                    icon = Icons.Default.Terrain,
                    onClick = { navController.navigate("lines") }
                )
            }
            item {
                FeatureCard(
                    title = "Mappa Interattiva",
                    description = "Visualizza aree e percorsi",
                    icon = Icons.Default.Map,
                    onClick = { navController.navigate("map") }
                )
            }
            item {
                FeatureCard(
                    title = "Info e Biglietti",
                    description = "Tutti i dettagli sul festival e come partecipare",
                    icon = Icons.Default.ConfirmationNumber,
                    onClick = { navController.navigate("about") }
                )
            }
            item {
                FeatureCard(
                    title = "Vai al Festival",
                    description = "Apri navigazione e copia coordinate",
                    icon = Icons.Default.Navigation,
                    onClick = {
                                                // Crea intent generico per la navigazione
                        val gmmIntentUri = Uri.parse("google.navigation:q=45.432139,7.259361")
                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)

                        // Mostra il chooser per scegliere l'app di navigazione
                        val chooserIntent = Intent.createChooser(mapIntent, "Scegli app di navigazione")
                        if (mapIntent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(chooserIntent)
                        } else {
                            // Fallback browser se nessuna app disponibile
                            val browserIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://www.google.com/maps/dir/?api=1&destination=45.432139,7.259361")
                            )
                            context.startActivity(browserIntent)
                        }
                    }

                )
            }
            item {
                FeatureCard(
                    title = "Download Orari Autobus",
                    description = "Visualizza gli orari del bus per Ceresole",
                    icon = Icons.Default.DirectionsBus,
                    onClick = {
                        val url = "https://www.gtt.to.it/cms/risorse/intercomunale/oraripdf/5137.pdf"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
                )
            }
            item {
                CountdownFeatureCard()
            }
        }

    }
}

fun daysUntilTarget(): Long {
    val today = LocalDate.now()
    val targetDate = LocalDate.of(2025, 9, 25)
    return ChronoUnit.DAYS.between(today, targetDate).coerceAtLeast(0)
}

fun iconForDaysLeft(days: Long): ImageVector {
    return when {
        days == 0L -> Icons.Default.Star // Oggi è il grande giorno!
        days <= 7 -> Icons.Default.Timer // Meno di una settimana
        days <= 30 -> Icons.Default.CalendarToday // Meno di un mese
        else -> Icons.Default.Event // Più di un mese
    }
}

@Composable
fun CountdownFeatureCard() {
    val daysLeft = daysUntilTarget()
    val icon = iconForDaysLeft(daysLeft)

    FeatureCard(
        title = "Countdown",
        description = "Mancano $daysLeft giorni al 25 settembre 2025",
        icon = icon,
        onClick = null // NON cliccabile
    )
}

@Composable
fun FeatureCard(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: (() -> Unit)? = null  // onClick opzionale, default null
) {
    Card(
        onClick = onClick ?: {},
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = MaterialTheme.shapes.medium,
        enabled = onClick != null
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 16.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            if (onClick != null) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
