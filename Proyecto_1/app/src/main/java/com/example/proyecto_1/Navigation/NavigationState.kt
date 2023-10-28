package com.example.proyecto_1.Navigation
sealed class NavigationState(val route: String) {
    object Temas_Clases: NavigationState("Temas_Clases")
    object ProfileType: NavigationState("ProfileType")
    object Preguntas: NavigationState("Preguntas")
    object Listado_de_lugares: NavigationState("Listado_de_lugares")
    object Mi_perfil: NavigationState("Mi_perfil")
    object Favoritos: NavigationState("Favoritos")
    object Log_in: NavigationState("Log_in")


}