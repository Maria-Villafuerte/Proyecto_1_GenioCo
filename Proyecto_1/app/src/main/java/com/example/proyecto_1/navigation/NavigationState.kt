package com.example.proyecto_1.navigation

sealed class NavigationState(val route: String) {
    object Welcome: NavigationState("welcome")
    object Login: NavigationState("login")
    object Register: NavigationState("register")
    object Congrats: NavigationState("felicidad")
    object Fail: NavigationState("Derrota")
    object WelcomeLogin: NavigationState("welcome_login/{userID}")
    object Home: NavigationState("HomeScreen/{userID}")
    object ProfilePage: NavigationState("ProfilePage/{userID}")
    object Topics: NavigationState("Topics/{classID}")
    object Selection: NavigationState("Selection/{themeID}")
    object Questions: NavigationState("Questions/{themeID}")
    object History: NavigationState("historial/{themeID}")
}