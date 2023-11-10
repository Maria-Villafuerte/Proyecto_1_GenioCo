package com.example.proyecto_1.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController


@ExperimentalMaterial3Api
@Composable
fun AppBar(title: String, navController: NavController) {
    TopAppBar(
        title = {
            Text(
                title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Localized description"
                )
            }

        },
        actions = {

            IconButton(onClick = { navController.navigate(route = NavigationState.Home.route) }) {
                Icon(
                    imageVector = Icons.Rounded.Home,
                    contentDescription = "Localized description"
                )
            }
            //Detalle
            IconButton(onClick = { navController.navigate(route = NavigationState.Selection.route)}) {
                Icon(
                    imageVector = Icons.Rounded.Info,
                    contentDescription = "Localized description"
                )
            }
            //Listado_de_lugares
            IconButton(onClick = { navController.navigate(route = NavigationState.Selection.route) }) {
                Icon(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = "Localized description"
                )
            }
            //Mi_perfil
            IconButton(onClick = { navController.navigate(route = NavigationState.ProfilePage.route)}) {
                Icon(
                    imageVector = Icons.Rounded.Face,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}