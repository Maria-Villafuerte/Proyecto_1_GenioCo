package com.example.proyecto_1.ui.temas.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_1.Navigation.AppBar
import com.example.proyecto_1.R
import com.example.proyecto_1.models.Parciales
import com.example.proyecto_1.models.Temas

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Temas_Clases(navController: NavController = rememberNavController()) {
    val tema_1 = Temas()
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
    val parcial_1 = Parciales("Parciale 1", alltemas_1)
    val parcial_2 = Parciales("Parciale 1", alltemas_2)

    //ARRAY QUE ENTRA A LAZY COLUMN
    val allparciales = remember { mutableStateListOf(
        parcial_1,parcial_2
    ) }

    Scaffold(bottomBar = {  }) {

        TopAppBar(title = { Text(text = "Tus Temas") })
        LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, top = 50.dp)){

            items(allparciales){parciales ->
                Row(parciales)
            }
        }
    }
    AppBar(title = "Parciales", navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Row(parcial: Parciales = Parciales()){
    Text(text = parcial.Nombre)
    LazyRow(){
        items(parcial.temas) { Temas ->
            TemaCard(Temas) }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemaCard(tema: Temas = Temas()){
    Card(modifier = Modifier //Especificaciones para visibilidad de carta
        .clip(RoundedCornerShape(3.dp))
        .fillMaxWidth()
        .padding(end = 20.dp),
        onClick = {}) {
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