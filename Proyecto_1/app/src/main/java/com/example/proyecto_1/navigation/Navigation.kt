package com.example.proyecto_1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_1.models.Questions
import com.example.proyecto_1.ui.bienvenida.view.WelcomeScreen
import com.example.proyecto_1.ui.bienvenida.view.WelcomeLoginScreen
import com.example.proyecto_1.ui.login.view.LoginScreen
import com.example.proyecto_1.ui.materia.view.HomeScreen
import com.example.proyecto_1.ui.preguntas.view.Felicidades

import com.example.proyecto_1.ui.selection.view.ProfileType
import com.example.proyecto_1.ui.historial.view.AnsweredQuestions
import com.example.proyecto_1.ui.perfil.view.ProfilePage
import com.example.proyecto_1.ui.preguntas.view.Derrota
import com.example.proyecto_1.ui.preguntas.view.Preguntas
import com.example.proyecto_1.ui.register.view.RegisterScreen
import com.example.proyecto_1.ui.temas.view.Temas_Clases

@Composable
fun Navigation_confi() {
    val navController = rememberNavController()
    val pregunta = Questions()
    NavHost(navController = navController,
        startDestination = NavigationState.Welcome.route) {
        composable(route = NavigationState.Temas_Clases.route) {
            Temas_Clases(navController)
        }
        composable(route = NavigationState.ProfileType.route) {
            ProfileType(navController)
        }
        composable(route = NavigationState.Welcome.route) {
            WelcomeScreen(navController)
        }
        composable(route = NavigationState.WelcomeLogin.route) {
            WelcomeLoginScreen(navController)
        }
        composable(route = NavigationState.Home.route) {
            HomeScreen(navController)
        }
        composable(route = NavigationState.Login.route) {
            LoginScreen(navController)
        }
        composable(route = NavigationState.Register.route){
            RegisterScreen(navController)
        }
        composable(route = NavigationState.Felicidades.route){
            Felicidades(navController)
        }
        composable(route = NavigationState.Derrota.route){
            Derrota(navController)
        }
        composable(route = NavigationState.History.route){
            AnsweredQuestions(navController)
        }
        composable(route = NavigationState.ProfilePage.route){
            ProfilePage(navController)
        }
        composable(route=NavigationState.Preguntas.route){
            Preguntas(navController)
        }
    }

}
