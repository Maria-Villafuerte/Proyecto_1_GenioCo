package com.example.proyecto_1.navegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.proyecto_1.ui.historial.view.AnsweredQuestions
import com.example.proyecto_1.ui.materia.view.HomeScreen
import com.example.proyecto_1.ui.perfil.view.ProfilePage

typealias ComposableFun = @Composable () -> Unit

/*
 * Tab Navigation items
 * sealed class que permite establecer las rutas de movimiento para la barra en la app
 */
sealed class TabItem(var icon: ImageVector, var title: String, var screen: ComposableFun) {
    object History : TabItem(Icons.Filled.Refresh, "History", { AnsweredQuestions() })
    object Home : TabItem(Icons.Filled.Home, "HomeScreen", { HomeScreen() })
    object Profile : TabItem(Icons.Filled.Face, "Profile", { ProfilePage() }) //Perfil
}