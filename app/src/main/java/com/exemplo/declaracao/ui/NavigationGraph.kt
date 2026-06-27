package com.exemplo.declaracao.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.exemplo.declaracao.screens.DeclarationScreen
import com.exemplo.declaracao.screens.HomeScreen
import com.exemplo.declaracao.screens.ZplScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("declaration") { DeclarationScreen() }
        composable("zpl") { ZplScreen() }
    }
}
