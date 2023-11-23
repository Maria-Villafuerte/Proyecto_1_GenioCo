package com.example.proyecto_1.ui.temas.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AddCircle
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_1.Networking.Realtime_Manager
import com.example.proyecto_1.navigation.AppBar
import com.example.proyecto_1.R
import com.example.proyecto_1.models.Clase
import com.example.proyecto_1.models.Parciales
import com.example.proyecto_1.models.Questions
import com.example.proyecto_1.models.Temas

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Temas_Clases(navController: NavController = rememberNavController(), userID: String="", classID: String="", quizID: String="") {
    val question1 = Questions(
        "0","Matematicas", "¿Cuál es la derivada de x^2 con respecto de x",
        "2x",
        listOf(
            "2x", "x", "2", "1"
        )
    )
    val preguntas= listOf<Questions>(question1)
    val tema1 = Temas(Preguntas = preguntas)
    val tema2 = Temas(Portada = R.drawable.portada2_clase)

    //TEMAS
    val alltemas1 = remember {
        mutableStateListOf(
            tema1, tema1, tema1, tema1
        )
    }
    val alltemas2 = remember {
        mutableStateListOf(
            tema2, tema2, tema2, tema2
        )
    }
    listOf<String>("AA","AA","WW")


    //PARCIALES
    val parcial1 = Parciales("1","Parcial 1", alltemas1)
    val parcial2 = Parciales("2","Parcial 2", alltemas2)
    val parcial3 = Parciales("3","Parcial 3", alltemas1)

    //ARRAY QUE ENTRA A LAZY COLUMN
    val allparciales = remember { mutableStateListOf(
        parcial1,parcial2,parcial3
    ) }

    var showDialog_paciales by remember { mutableStateOf(false) }
    var showDialog_temas by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { AppBar(title = stringResource(R.string.titulo_temas), navController = navController, userID = userID) },
        floatingActionButton = {
            Column (modifier = Modifier .padding(0.dp)){
                FloatingActionButton(
                    onClick = { showDialog_paciales = true },
                    modifier = Modifier
                        .width(200.dp)
                        .height(40.dp)
                        .align(CenterHorizontally)
                        .padding(bottom = 10.dp)
                ) {
                    androidx.compose.foundation.layout.Row (modifier = Modifier .padding(0.dp)){
                        Text(text = "Agrega Parciales")
                    }
                }

                FloatingActionButton(
                    onClick = { showDialog_temas = true },
                    modifier = Modifier
                        .width(200.dp)
                        .height(30.dp)
                        .align(CenterHorizontally)

                ) {
                    androidx.compose.foundation.layout.Row (modifier = Modifier .padding(0.dp)){
                        Text(text = "Agrega Tema")
                    }
                }
            }

        },
        floatingActionButtonPosition = FabPosition.End, // opcional, puedes ajustar la posición según tus necesidades

    ) {
        if (showDialog_paciales) {
            pop_up_parciales(userID, classID,showDialog_paciales) {
                showDialog_paciales = false
            }
        }
        if (showDialog_temas) {
            pop_up_temas(userID, classID,showDialog_temas) {
                showDialog_temas = false
            }
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_Xsmall)),
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    dimensionResource(R.dimen.padding_big),
                    top = dimensionResource(R.dimen.padding_extraB)
                )){

            items(allparciales){parciales ->
                Row(parciales,navController, userID, classID)
            }
        }

    }
}

@Composable
fun Row(parcial: Parciales = Parciales(),  navController: NavController, userID: String, classID: String){
    Text(text = parcial.Nombre)
    LazyRow{
        items(parcial.temas) { tema ->
            TemaCard(tema,navController, userID, classID)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemaCard(tema: Temas = Temas(), navController: NavController, userID: String, classID: String){
    Card(modifier = Modifier //Especificaciones para visibilidad de carta
        .clip(RoundedCornerShape(dimensionResource(R.dimen.roundClip)))
        .width(dimensionResource(R.dimen.height_profileBack))
        .padding(end = dimensionResource(R.dimen.padding_big)),
        onClick = {
            navController.navigate(route = "Selection/$userID/$classID/" + tema.id)
        }
    ) {
        Text(text = tema.Nombre, fontSize = 15.sp,
            modifier=Modifier.padding(dimensionResource(R.dimen.padding_small)),
            fontWeight = FontWeight.Bold)
        Image(painter = painterResource(id = tema.Portada),
            contentDescription = "Portada ilustrativa",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pop_up_temas(userID: String, clase:String,showDialog: Boolean, onDismiss: () -> Unit) {
    val real = Realtime_Manager()
    var parcial by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                onDismiss()
            },
            title = { Text(stringResource(R.string.Agregar_tema)) },
            confirmButton = {
                Column() {
                    OutlinedTextField(
                        value = parcial,
                        onValueChange = {newText -> parcial = newText},
                        label = {Text(stringResource(R.string.Agregar_tema3) )}
                    )
                    OutlinedTextField(
                        value = name,
                        onValueChange = {newText -> name = newText},
                        label = {Text(stringResource(R.string.Agregar_tema1) )}
                    )

                    OutlinedTextField(
                        value = id,
                        onValueChange = {newText -> id = newText},
                        label = {Text(stringResource(R.string.Agregar_tema2)) }
                    )
                    TextButton(
                        onClick = {
                            var tema_nuevo: Temas = Temas(id, name)
                            real.agregar_tema(userID,clase,parcial,tema_nuevo)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pop_up_parciales(userID: String, clase:String,showDialog: Boolean, onDismiss: () -> Unit) {
    val real = Realtime_Manager()
    var name by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                onDismiss()
            },
            title = { Text(stringResource(R.string.Agregar_parcial)) },
            confirmButton = {
                Column() {

                    OutlinedTextField(
                        value = name,
                        onValueChange = {newText -> name = newText},
                        label = {Text(stringResource(R.string.Agregar_parcial1) )}
                    )

                    OutlinedTextField(
                        value = id,
                        onValueChange = {newText -> id = newText},
                        label = {Text(stringResource(R.string.Agregar_parcial2)) }
                    )
                    TextButton(
                        onClick = {
                            var parcial_nuevo: Parciales = Parciales(id, name)
                            real.agregar_parcial(userID,clase,parcial_nuevo)
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