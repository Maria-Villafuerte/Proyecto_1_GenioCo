package com.example.proyecto_1.ui.login.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto_1.R
import com.example.proyecto_1.navigation.NavigationState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally // Alinea los elementos a la izquierda
    ) {
        Text(
            text = stringResource(R.string.bienveida_2),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(R.string.datos_1),
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.correo)) },
            modifier = Modifier
                .width(dimensionResource(R.dimen.width_textBox))
                .padding(dimensionResource(R.dimen.padding_Xsmall))
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.contra))},
            modifier = Modifier
                .width(dimensionResource(R.dimen.width_textBox))
                .padding(dimensionResource(R.dimen.padding_Xsmall))
        )
        TextButton(onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.End)
                .height(dimensionResource(R.dimen.height_textbutton)))
        {Text(stringResource(R.string.contra_1)) }
        val userID = "abc"//PENDIENTE REVISAR OBTENCION DE ID DE USUARIO
        Button(
            onClick = {navController.navigate(route = "welcome_login/$userID")},
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.height_button))
                .width(dimensionResource(R.dimen.width_textBox))
                .padding(dimensionResource(R.dimen.padding_Xsmall))
        ) {
            Text(text = stringResource(R.string.inicio))
        }
        TextButton(onClick = {navController.navigate(route = NavigationState.Register.route) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .height(dimensionResource(R.dimen.height_textbutton)))
        {Text(stringResource(R.string.cambio_registro)) }
    }
}
