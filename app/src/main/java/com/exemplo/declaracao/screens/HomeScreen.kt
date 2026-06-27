package com.exemplo.declaracao.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Declaração ZPL", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { navController.navigate("declaration") }, modifier = Modifier.fillMaxWidth()) {
            Text("Declaração de Conteúdo")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("zpl") }, modifier = Modifier.fillMaxWidth()) {
            Text("Conversor ZPL para PDF")
        }
    }
}
