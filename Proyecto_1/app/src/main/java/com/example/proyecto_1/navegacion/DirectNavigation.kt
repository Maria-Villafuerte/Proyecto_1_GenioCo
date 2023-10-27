package com.example.proyecto_1.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_1.ui.bienvenida.view.WelcomeLoginScreen
import com.example.proyecto_1.ui.bienvenida.view.WelcomeScreen
import com.example.proyecto_1.ui.login.view.LoginScreen

/*
 * Rutas de navegación
 */
sealed class NavigationState(val route: String) {
    object Welcome: NavigationState("welcome")
    object WelcomeLogin: NavigationState("welcome_login")
    object Tabs: NavigationState("Tabs")
    object Login: NavigationState("login") //Login usuario

    //PANTALLAS DE NAVEGACION DIRECTA
}

/*
    NavHost, navegación directa
*/
@Composable
fun DirectNavigation(modifier: Modifier = Modifier) {
    NavHost(navController = rememberNavController(),
        startDestination = NavigationState.Welcome.route, //Inicia en el Grid de eventos
        modifier = modifier) {
        composable(route = NavigationState.Welcome.route) {
            WelcomeScreen()
        }
        composable(route = NavigationState.WelcomeLogin.route) {
            WelcomeLoginScreen()
        }
        composable(route = NavigationState.Tabs.route) {
            TabNavigation()
        }
        composable(route = NavigationState.Login.route) {
            LoginScreen()
        }
    }
}

