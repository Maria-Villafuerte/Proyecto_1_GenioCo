package com.example.proyecto_1.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.proyecto_1.ui.profileType.view.ProfileType
import com.example.proyecto_1.ui.temas.view.Temas_Clases

@Composable
fun Navigation_confi(navController: NavController) {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = NavigationState.Temas_Clases.route) {
        composable(route = NavigationState.Temas_Clases.route) {
            Temas_Clases(navController)
        }
        composable(route = NavigationState.ProfileType.route) {
            ProfileType(navController)
        }
    }
}