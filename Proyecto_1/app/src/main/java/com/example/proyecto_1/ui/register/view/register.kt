package com.example.proyecto_1.ui.register.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto_1.R
import com.example.proyecto_1.navigation.NavigationState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
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
            stringResource(R.string.bienveida_2),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            stringResource(R.string.datos_1),
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                stringResource(R.string.correo)
                    },
            modifier = Modifier
                .width(350.dp)
                .padding(5.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text( stringResource(R.string.contra))},
            modifier = Modifier
                .width(350.dp)
                .padding(5.dp)
        )
        TextButton(onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.End)
                .height(40.dp))
        {Text(stringResource(R.string.contra_1)) }

        Button(
            onClick = {navController.navigate(route = NavigationState.Home.route)},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .width(350.dp)
                .padding(5.dp)
        ) {
            Text(stringResource(R.string.registro))
        }
    }
}