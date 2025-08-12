package com.tsf.valleorcohighlinefestival

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen() {
    val context = LocalContext.current

    Scaffold(
        topBar = { TopAppBar(title = { Text("Contatti") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            // 📞 Numero emergenza
            ContactSection(
                title = "Emergenza",
                description = "Chiama il numero di sicurezza del festival",
                buttonText = "Chiama 112"
            ) {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:112"))
                context.startActivity(intent)
            }

            // 📧 Email
            ContactSection(
                title = "Contatta gli organizzatori",
                description = "Per info e supporto",
                buttonText = "Invia email"
            ) {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:info@highlinefestival.it")
                    putExtra(Intent.EXTRA_SUBJECT, "Info Festival")
                }
                context.startActivity(intent)
            }

            // 🔗 Social
            ContactSection(
                title = "Seguici sui social",
                description = "Aggiornamenti, foto, eventi live",
                buttonText = "Instagram"
            ) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/highlinefestival"))
                context.startActivity(intent)
            }
        }
    }
}

@Composable
fun ContactSection(
    title: String,
    description: String,
    buttonText: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(description, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onClick) {
                Text(buttonText)
            }
        }
    }
}
