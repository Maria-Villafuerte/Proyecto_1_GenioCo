package com.example.proyecto_1.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Home
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
fun AppBar(title: String, navController: NavController, userID: String) {
    TopAppBar(
        title = {
            Text(
                title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        },
        actions = {
            IconButton(onClick = { navController.navigate(route = "HomeScreen/$userID") }) {
                Icon(
                    imageVector = Icons.Rounded.Home,
                    contentDescription = "Localized description"
                )
            }
            //Mi_perfil
            IconButton(onClick = { navController.navigate(route = "ProfilePage/$userID")}) {
                Icon(
                    imageVector = Icons.Rounded.Face,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}