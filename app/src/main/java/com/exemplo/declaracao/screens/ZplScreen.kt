package com.exemplo.declaracao.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ZplScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Conversor ZPL para PDF", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Funcionalidade em desenvolvimento.\n\nUse um editor de texto para colar o ZPL e converter via Labelary (será implementado em breve).")
        // Aqui podemos adicionar TextField para ZPL + botão para gerar PDF via API
    }
}
