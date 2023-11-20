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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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

    Scaffold(
        topBar = { AppBar(title = stringResource(R.string.titulo_temas), navController = navController, userID = userID) }) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_Xsmall)),
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_big), top = dimensionResource(R.dimen.height_question))){

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