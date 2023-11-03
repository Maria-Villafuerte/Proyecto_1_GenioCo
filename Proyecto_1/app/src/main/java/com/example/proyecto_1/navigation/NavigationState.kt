package com.example.proyecto_1.navigation

sealed class NavigationState(val route: String) {
    object Welcome: NavigationState("welcome")
    object Login: NavigationState("login") //Login usuario
    object Register: NavigationState("register")
    object Felicidades: NavigationState("felicidad")
    object Derrota: NavigationState("Derrota")
    object WelcomeLogin: NavigationState("welcome_login/{user}")
    object Home: NavigationState("HomeScreen/{user}")
    object ProfilePage: NavigationState("ProfilePage/{user}")
    object TemasClases: NavigationState("TemasClases/{clase}")
    object Selection: NavigationState("Selection/{theme}")
    object Preguntas: NavigationState("Preguntas/{theme}")
    object History: NavigationState("historial/{theme}")


}