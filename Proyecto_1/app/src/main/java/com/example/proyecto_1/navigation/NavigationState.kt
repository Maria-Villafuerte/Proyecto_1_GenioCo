package com.example.proyecto_1.navigation

sealed class NavigationState(val route: String) {
    object Welcome: NavigationState("welcome")
    object Login: NavigationState("login")
    object Register: NavigationState("register")
    object Congrats: NavigationState("felicidad")
    object Fail: NavigationState("Derrota")
    object WelcomeLogin: NavigationState("welcome_login/{user}")
    object Home: NavigationState("HomeScreen/{user}")
    object ProfilePage: NavigationState("ProfilePage/{user}")
    object Topics: NavigationState("Topics/{clase}")
    object Selection: NavigationState("Selection/{tema}")
    object Questions: NavigationState("Questions/{tema}")
    object History: NavigationState("historial/{tema}")


}