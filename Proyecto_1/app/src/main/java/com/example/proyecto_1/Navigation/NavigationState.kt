package com.example.proyecto_1.Navigation

import com.example.proyecto_1.models.Questions

sealed class NavigationState(val route: String) {
    object Temas_Clases: NavigationState("Temas_Clases")
    object ProfileType: NavigationState("ProfileType")
    object Preguntas: NavigationState("Preguntas")
    object History: NavigationState("historial")
    object Welcome: NavigationState("welcome")
    object WelcomeLogin: NavigationState("welcome_login")
    object Home: NavigationState("HomeScreen")
    object Login: NavigationState("login") //Login usuario
    object Register: NavigationState("register")
    object Felicidades: NavigationState("felicidad")
    object Derrota: NavigationState("Derrota")
    object ProfilePage: NavigationState("ProfilePage")



}