package com.example.proyecto_1.ui.bienvenida.view

import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
                .padding(dimensionResource(R.dimen.padding_small),
                    bottom = dimensionResource(R.dimen.padding_extraB)),
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
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "books decoration",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Text(textAlign = TextAlign.Center,
            text=stringResource(R.string.nombre_app)+"\n\n"+stringResource(R.string.bienvenido),
            fontSize = 40.sp, fontWeight = FontWeight.ExtraBold,
            color = Color(R.color.teal_700))

        // Aparecer por medio segundo y transicionar a la siguiente pantalla
        LaunchedEffect(Unit) {
            delay(500) // Retraso de 500 milisegundos (0.5 segundos)
            navController.navigate(route = "HomeScreen/$userID")
        }
    }
}
