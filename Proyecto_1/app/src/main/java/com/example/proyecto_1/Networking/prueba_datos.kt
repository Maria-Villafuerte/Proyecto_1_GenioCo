package com.example.proyecto_1.Networking

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_1.models.Clase
import com.example.proyecto_1.models.User
import com.google.firebase.Firebase
import com.google.firebase.database.database

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun prueba_datos(){
    val database = Firebase.database

    var name by remember {mutableStateOf("")}
    var id by remember {mutableStateOf("")}
    var mail by remember {mutableStateOf("")}
    var password by remember {mutableStateOf("")}
    var inputText by remember { mutableStateOf("") }
    var clases by remember { mutableStateOf(emptyList<Clase>()) }



    Column(modifier = Modifier.padding(30.dp)) {
        TextField(value = name,
            onValueChange = {newText -> name = newText},
            label = { Text(text = "Name")}
        )
        TextField(value = id,
            onValueChange = {newText -> id = newText},
            label = { Text(text = "id")}
        )
        TextField(value = mail,
            onValueChange = {newText -> mail = newText},
            label = { Text(text = "mail")}
        )
        TextField(value = password,
            onValueChange = {newText -> password = newText},
            label = { Text(text = "password")}
        )
        TextField(
            value = inputText,
            onValueChange = {
                inputText = it
                // Convierte el texto a una lista de clases (puedes personalizar esta lógica según tu necesidad)
                val newList = it.lines().map { claseName -> Clase(Nombre = claseName) }
                clases = newList
            },
            label = { Text(text = "Lista de Clases") },
            maxLines = 5, // Puedes ajustar esto según tus necesidades
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        val context = LocalContext.current
        Button(
            onClick = {
                val usersRef = database.reference.child("Usuarios")
                val userRef = usersRef.child(name)
                val usuario = User(id,name)
                userRef.setValue(usuario)
                Toast.makeText(context, "Save user", Toast.LENGTH_SHORT).show()
                name = ""
                id = ""
            },
            modifier = Modifier.padding(30.dp)
        ) {
            Text(text = "Save")
            
        }

    }

}