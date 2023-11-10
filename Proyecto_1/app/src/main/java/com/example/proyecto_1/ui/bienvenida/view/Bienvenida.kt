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
import androidx.compose.ui.res.stringResource
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
                stringResource(R.string.bienvenido),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )
            Button(onClick = {navController.navigate(route = NavigationState.Login.route)}) {
                Text(stringResource(R.string.inicio))
            }
            Button(onClick = {navController.navigate(route = NavigationState.Register.route) }) {
                Text(stringResource(R.string.registro))
            }
        }
    }
}

@Composable
fun WelcomeLoginScreen(navController: NavController, userID: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "books decoration",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        TextButton(
            modifier = Modifier.align(Alignment.Center),
            content = { Text(stringResource(R.string.nombre_app)+"\n"+stringResource(R.string.bienvenido))},
            onClick = {navController.navigate(route = "HomeScreen/$userID")}
        )
    }
}
