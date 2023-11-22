package com.example.proyecto_1.navigation

sealed class NavigationState(val route: String) {
    object Welcome: NavigationState("welcome")
    object Login: NavigationState("login")
    object Register: NavigationState("register")
    object WelcomeLogin: NavigationState("welcome_login/{userID}")
    object Home: NavigationState("HomeScreen/{userID}")
    object ProfilePage: NavigationState("ProfilePage/{userID}")
    object Topics: NavigationState("Topics/{userID}/{classID}/{quizID}")
    object Selection: NavigationState("Selection/{userID}/{classID}/{quizID}/{themeID}")
    object History: NavigationState("historial/{userID}/{classID}/{quizID}/{themeID}")
    object Questions: NavigationState("Questions/{userID}/{classID}/{quizID}/{themeID}/{questionID}")
    object Congrats: NavigationState("felicidad/{userID}/{classID}/{quizID}/{themeID}/{questionID}")
    object Fail: NavigationState("Derrota/{userID}/{classID}/{quizID}/{themeID}/{questionID}")
}