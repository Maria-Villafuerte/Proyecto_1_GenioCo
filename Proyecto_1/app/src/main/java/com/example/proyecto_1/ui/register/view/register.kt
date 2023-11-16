package com.example.proyecto_1.ui.register.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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
    val database = Firebase.database
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var total_datos by remember { mutableStateOf("") }

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
            value = name,
            onValueChange = {newText -> name = newText},
            label = {Text("Nombre Usuario") },
            modifier = Modifier
                .width(350.dp)
                .padding(5.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = {newText -> email = newText},
            label = {Text(stringResource(R.string.correo)) },
            modifier = Modifier
                .width(350.dp)
                .padding(5.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { newText -> password = newText },
            label = { Text( stringResource(R.string.contra))},
            modifier = Modifier
                .width(350.dp)
                .padding(5.dp)
        )
        TextButton(onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.End)
                .height(40.dp))
        {Text(stringResource(R.string.contra_1)) }
        val context = LocalContext.current
        val openAlertDialog = remember { mutableStateOf(false) }
        val usersRef = database.reference.child("Usuarios")

        usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // El recuento total de nodos bajo "Usuarios"
                val totalDatos = snapshot.childrenCount.toInt()
                total_datos = (totalDatos + 1).toString()
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
                val userRef = usersRef.child(total_datos)
                val userID = total_datos
                val usuario = User(id = total_datos,name=name,mail = email, password = password)
                //Guarda el dato como un hijo del total que se rige por id
                if (usuario.name != "") {
                    userRef.setValue(usuario)
                    Toast.makeText(context, savedUsertext , Toast.LENGTH_SHORT).show()
                    navController.navigate("welcome_login/$userID")
                }
                else {
                    Toast.makeText(context, nodata , Toast.LENGTH_SHORT).show()
                }
                total_datos = ""
                email = ""
                password = ""
                name = ""
                openAlertDialog.value = true
                      },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .width(350.dp)
                .padding(5.dp)
        ) {
            Text(stringResource(R.string.registro))
        }
        TextButton(onClick = {navController.navigate(route = NavigationState.Login.route)},
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .height(40.dp))
        {Text(stringResource(R.string.cambio_inicio)) }

    }
}

//




/*
        if (openAlertDialog.value) {
            AlertDialog(
                icon = {
                    Icon(Icons.Rounded.AccountCircle, contentDescription = "Example Icon")
                },
                title = {
                    Text(text = "¿Como quieres que te llamemos?")
                }        ,
                text = {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = {Text("nombre") },
                        modifier = Modifier
                            .width(350.dp)
                            .padding(5.dp)
                    )
                },
                onDismissRequest = {
                    openAlertDialog.value = false
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val updates = mapOf<String, Any>(
                                "nombre" to name,
                            )
                            userRef_3.orderByChild("name").equalTo("name").addListenerForSingleValueEvent(
                                object : ValueEventListener {
                                    override fun onDataChange(snapshot: DataSnapshot) {
                                        for (userSnapshot in snapshot.children) {
                                            // Obtener la referencia del usuario específico
                                            val userRef = userSnapshot.ref

                                            // Actualizar los datos del usuario
                                            userRef.updateChildren(updates)
                                                .addOnSuccessListener {
                                                    // La actualización fue exitosa
                                                    Toast.makeText(context, "Nombre actualizado", Toast.LENGTH_SHORT).show()
                                                }
                                                .addOnFailureListener {
                                                    // Hubo un error en la actualización
                                                    Toast.makeText(context, "Error al actualizar el nombre", Toast.LENGTH_SHORT).show()
                                                }
                                        }
                                    }

                                    override fun onCancelled(error: DatabaseError) {
                                        // Manejar la cancelación si es necesario
                                    }
                                })
                            println("Confirmation registered")
                            name=""
                            //navController.navigate(route = "HomeScreen/${usuario.id}")
                            openAlertDialog.value = false
                        }
                    ) {
                        Text("Confirm")

                    }
                }
            )
        }
 */
