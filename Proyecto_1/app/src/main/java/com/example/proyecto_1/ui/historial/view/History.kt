package com.example.proyecto_1.ui.historial.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_1.Networking.Realtime_Manager
import com.example.proyecto_1.R
import com.example.proyecto_1.models.Questions
import com.example.proyecto_1.navigation.AppBar
import com.example.proyecto_1.ui.historial.viewmodel.recoverQuestionsH

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AnsweredQuestions(navController: NavController = rememberNavController(), userID: String="0", classID: String="7657", quizID: String="1", themeID: String = "35"){
    val preguntasLiveData = remember { MutableLiveData<List<Questions>>() }
    val realtime = Realtime_Manager()
    val referenceParcial = realtime.databaseReference.child(userID).child("Clases").child(classID).child("Parciales")
    val referencePreguntas = referenceParcial.child(quizID).child("Temas").child(themeID).child("Preguntas")

    // Obtener datos de Firebase
    referencePreguntas.get().addOnSuccessListener { dataSnapshot ->
        val preguntas = mutableListOf<Questions>()
        dataSnapshot.children.forEach { snapshot ->
            val id = snapshot.child("id").value.toString()
            val respuesta = snapshot.child("respuesta").value.toString()
            val pregunta0 = snapshot.child("pregunta").value.toString()
            val opciones = snapshot.child("opciones").value as ArrayList<String>
            val pregunta = Questions(id, "", pregunta0, respuesta, opciones.toList())
            preguntas.add(pregunta)
        }
        preguntasLiveData.value = preguntas
    }.addOnFailureListener { exception ->
        Log.e("firebase", "Error getting data", exception)
    }

    // Observar el LiveData y usar los datos en la UI
    val allQuestions by preguntasLiveData.observeAsState(emptyList())

    var showDialog_pregunta by remember { mutableStateOf(false) }
    Scaffold(
        topBar = { AppBar(stringResource(R.string.titulo_hisotry), navController= navController, userID= userID) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showDialog_pregunta = true
                }) {
                Icon(Icons.Rounded.Add, contentDescription = "Localized description")
            }
        },
        floatingActionButtonPosition = FabPosition.End, // opcional, puedes ajustar la posición según tus necesidades

    ) {
        if (showDialog_pregunta) {
            pop_up_pregunta(userID,classID,classID,themeID,showDialog_pregunta) {
                showDialog_pregunta = false
            }
        }
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))){
            TopAppBar(title = { Text(stringResource(R.string.titulo_hisotry))})
            LazyVerticalGrid(columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))){
                items(allQuestions) {pregunta ->
                    Card (modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(dimensionResource(R.dimen.padding_small))){
                        Text(text = pregunta.pregunta + "\n" + pregunta.respuesta,
                            fontSize = 12.sp, fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(dimensionResource(R.dimen.padding_small)))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pop_up_pregunta(userID: String,clase:String,pacial:String,tema:String,showDialog: Boolean, onDismiss: () -> Unit) {
    val real = Realtime_Manager()
    var pregunta by remember { mutableStateOf("") }
    var respuesta1 by remember { mutableStateOf("") }
    var respuesta2 by remember { mutableStateOf("") }
    var respuesta3 by remember { mutableStateOf("") }
    var respuesta4 by remember { mutableStateOf("") }
    var array_opciones: MutableList<String> = mutableListOf()
    var id by remember { mutableStateOf("") }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                onDismiss()
            },
            title = { Text(stringResource(R.string.Agregar_pregunta)) },
            confirmButton = {
                Column() {
                    OutlinedTextField(
                        value = id,
                        onValueChange = {newText -> id = newText},
                        label = {Text(stringResource(R.string.Agregar_pregunta1)) }
                    )
                    OutlinedTextField(
                        value = pregunta,
                        onValueChange = {newText -> pregunta = newText},
                        label = {Text(stringResource(R.string.Agregar_pregunta2)) }
                    )
                    OutlinedTextField(
                        value = respuesta1,
                        onValueChange = { newText -> respuesta1 = newText },
                        label = { Text(stringResource(R.string.Agregar_pregunta3)) }
                    )
                    OutlinedTextField(
                        value = respuesta2,
                        onValueChange = { newText -> respuesta2 = newText },
                        label = { Text(stringResource(R.string.Agregar_pregunta4)) }
                    )
                    OutlinedTextField(
                        value = respuesta3,
                        onValueChange = { newText -> respuesta3 = newText },
                        label = { Text(stringResource(R.string.Agregar_pregunta4)) }
                    )
                    OutlinedTextField(
                        value = respuesta4,
                        onValueChange = { newText -> respuesta4 = newText },
                        label = { Text(stringResource(R.string.Agregar_pregunta4)) }
                    )

                    TextButton(
                        onClick = {
                            array_opciones.add(respuesta1)
                            array_opciones.add(respuesta2)
                            array_opciones.add(respuesta3)
                            array_opciones.add(respuesta4)
                            var presunta_nueva: Questions = Questions(id,"", pregunta,respuesta1,array_opciones)
                            real.agregar_pregunta(userID,clase,pacial,tema,presunta_nueva)
                            onDismiss()
                        },
                        modifier = Modifier
                            .padding(top = 10.dp)
                    ) {
                        Text((stringResource(R.string.agregar)), color = Color.Gray)
                    }
                }
            }
        )

    }

}
