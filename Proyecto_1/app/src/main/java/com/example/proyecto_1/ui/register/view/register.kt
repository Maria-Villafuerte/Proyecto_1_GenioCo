package com.example.proyecto_1.ui.register.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto_1.Networking.Realtime_Manager
import com.example.proyecto_1.R
import com.example.proyecto_1.models.User
import com.example.proyecto_1.navigation.NavigationState
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var totaldatos by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium)),
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
            value = name,
            onValueChange = {newText -> name = newText},
            label = {Text("Nombre Usuario") },
            modifier = Modifier
                .width(dimensionResource(R.dimen.width_textBox))
                .padding(dimensionResource(R.dimen.padding_Xsmall))
        )

        OutlinedTextField(
            value = email,
            onValueChange = {newText -> email = newText},
            label = {Text(stringResource(R.string.correo)) },
            modifier = Modifier
                .width(dimensionResource(R.dimen.width_textBox))
                .padding(dimensionResource(R.dimen.padding_Xsmall))
        )

        OutlinedTextField(
            value = password,
            onValueChange = { newText -> password = newText },
            label = { Text( stringResource(R.string.contra))},
            modifier = Modifier
                .width(dimensionResource(R.dimen.width_textBox))
                .padding(dimensionResource(R.dimen.padding_Xsmall))
        )
        TextButton(onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.End)
                .height(dimensionResource(R.dimen.padding_XXbig)))
        {Text(stringResource(R.string.contra_1)) }
        val context = LocalContext.current
        val openAlertDialog = remember { mutableStateOf(false) }
        val realtime = Realtime_Manager()

        realtime.databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // El recuento total de nodos bajo "Usuarios"
                val totalDatos = snapshot.childrenCount.toInt()
                totaldatos = (totalDatos + 1).toString()
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error si ocurre
                println("Error al obtener el recuento: ${error.message}")
            }
        })
        val savedUsertext = stringResource(R.string.registrado)
        val nodata = stringResource(R.string.noData)
        Button(
            onClick = {
                val userID = totaldatos
                val usuario = User(id = totaldatos,name=name,mail = email, password = password)
                //Guarda el dato como un hijo del total que se rige por id
                if (usuario.name != "") {
                    realtime.addContact(usuario)
                    Toast.makeText(context, savedUsertext , Toast.LENGTH_SHORT).show()
                    Toast.makeText(context, "Tu Id es: $userID", Toast.LENGTH_SHORT).show()

                    navController.navigate("welcome_login/$userID")
                }
                else {
                    Toast.makeText(context, nodata , Toast.LENGTH_SHORT).show()
                }
                totaldatos = ""
                email = ""
                password = ""
                name = ""
                openAlertDialog.value = true
                      },
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.padding_XXXbig))
                .width(dimensionResource(R.dimen.width_textBox))
                .padding(dimensionResource(R.dimen.padding_Xsmall))
        ) {
            Text(stringResource(R.string.registro))
        }
        TextButton(onClick = {navController.navigate(route = NavigationState.Login.route)},
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .height(dimensionResource(R.dimen.padding_XXbig)))
        {Text(stringResource(R.string.cambio_inicio)) }

    }
}