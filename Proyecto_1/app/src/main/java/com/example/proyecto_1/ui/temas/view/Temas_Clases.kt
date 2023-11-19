package com.example.proyecto_1.ui.temas.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_1.navigation.AppBar
import com.example.proyecto_1.R
import com.example.proyecto_1.models.Parciales
import com.example.proyecto_1.models.Questions
import com.example.proyecto_1.models.Temas

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Temas_Clases(navController: NavController = rememberNavController(), userID: String="", classID: String="") {
    val Question_1 = Questions(
        "0","Matematicas", "¿Cuál es la derivada de x^2 con respecto de x",
        "2x",
        listOf(
            "2x", "x", "2", "1"
        )
    )
    val Preguntas= listOf<Questions>(Question_1)
    val tema_1 = Temas(Preguntas = Preguntas)
    val tema_2 = Temas(Portada = R.drawable.portada2_clase)

    //TEMAS
    val alltemas_1 = remember {
        mutableStateListOf(
            tema_1, tema_1, tema_1, tema_1
        )
    }
    val alltemas_2 = remember {
        mutableStateListOf(
            tema_2, tema_2, tema_2, tema_2
        )
    }
    listOf<String>("AA","AA","WW")


    //PARCIALES
    val parcial_1 = Parciales("1","Parcial 1", alltemas_1)
    val parcial_2 = Parciales("2","Parcial 2", alltemas_2)
    val parcial_3 = Parciales("3","Parcial 3", alltemas_1)

    //ARRAY QUE ENTRA A LAZY COLUMN
    val allparciales = remember { mutableStateListOf(
        parcial_1,parcial_2,parcial_3
    ) }

    Scaffold(
        topBar = { AppBar(title = stringResource(R.string.titulo_temas), navController = navController, userID = userID) }) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, top = 100.dp)){

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
        .clip(RoundedCornerShape(3.dp))
        .width(300.dp)
        .padding(end = 20.dp),
        onClick = {
            navController.navigate(route = "Selection/$userID/$classID/" + tema.id)
        }
    ) {
        Text(text = tema.Nombre, fontSize = 15.sp,
            modifier=Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold)
        Image(painter = painterResource(id = tema.Portada),
            contentDescription = "Portada ilustrativa",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )

    }
}