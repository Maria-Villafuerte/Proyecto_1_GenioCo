package com.example.proyecto_1.ui.bienvenida.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto_1.R
import com.example.proyecto_1.navigation.NavigationState

@Composable
fun WelcomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "books decoration",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, bottom = 80.dp),
            verticalArrangement = Arrangement.Bottom, // Alinea los elementos en la parte inferior
            horizontalAlignment = Alignment.Start // Alinea los elementos a la izquierda
        ) {
            Text(
                text = "Bienvenido",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )
            Button(onClick = {navController.navigate(route = NavigationState.Login.route)}) {
                Text(text = "Iniciar sesión")
            }
            Button(onClick = {navController.navigate(route = NavigationState.Register.route) }) {
                Text(text = "Registrarse")
            }
        }
    }
}

@Composable
fun WelcomeLoginScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "books decoration",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        TextButton(
            modifier = Modifier.align(Alignment.Center),
            content = { Text(text = "GenioCo\nBienvenido")},
            onClick = {navController.navigate(route = NavigationState.Home.route)}
        )
    }
}
