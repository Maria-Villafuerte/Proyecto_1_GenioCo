package com.example.proyecto_1.ui.login.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController = rememberNavController()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally // Alinea los elementos a la izquierda
    ) {
        Text(
            text = "¡Bienvenido de nuevo!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Ingresa tus datos",
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            modifier = Modifier
                .width(350.dp)
                .padding(5.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier
                .width(350.dp)
                .padding(5.dp)
        )
        TextButton(onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.End)
                .height(40.dp))
        {Text("¿Olvidaste tu contraseña?") }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .width(350.dp)
                .padding(5.dp)
        ) {
            Text(text = "Iniciar Sesión")
        }
    }
}
