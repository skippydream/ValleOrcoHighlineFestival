package com.tsf.valleorcohighlinefestival

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen() {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Info Festival") }
            )
        }
    ) { paddingValues ->

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(scrollState), // <- AGGIUNTO
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Torino Sul Filo ha il piacere di presentare un inedito Slackline Meeting 🎉 in collaborazione con VOCF, a Ceresole Reale (Valle Orco)! Vieni a divertirti sulle nostre linee dal 25 al 28 settembre 2025!\n\n")

                    append("Durante l'evento verrà indetto un contest di freestyle e ci sarà la possibilità di partecipare a vari workshop.\n\n")

                    append("Alla fine del meeting ci sarà anche la possibilità di acquistare alcuni dei materiali utilizzati, grazie agli sponsor Raed Slacklines e Spider Slacklines 🧵\n\n")

                    pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                    append("🎟️ Il biglietto ha un prezzo di 40 Euro.\n")
                    pop()

                    pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                    append("Cosa include?\n")
                    pop()
                    append("- Accesso a oltre 10 linee (40–180 metri) per tutti e 4 i giorni\n")
                    append("- Una maglietta commemorativa 👕\n")
                    append("- Un sacco di gaso 😄\n\n")

                    pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                    append("Cosa non include?\n")
                    pop()
                    append("- Il parcheggio per auto/van 🚐 (da pagare all’arrivo all* ragazz* di VOCF)\n")
                    append("- L'accesso al workshop introduttivo all'highline")
                },
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(32.dp))

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/WQyArWHPPPySPe1F9"))
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ConfirmationNumber,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Compra Biglietto")
            }
        }
    }
}