package com.example.proyecto_1.ui.materia.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.proyecto_1.R
import com.example.proyecto_1.models.Clase

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    val defaultClass = Clase()
    val defaultClass2 = Clase(Portada = R.drawable.portada2_clase)
    val allClasses = remember { mutableStateListOf(
        defaultClass, defaultClass2, defaultClass, defaultClass2
    ) }
    LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)){
        item {
            //Columna deslizable, con elementos generales de clases
            TopAppBar(title = { Text(text = "Tus clases (～￣▽￣)～")}) }
        items(allClasses){clase ->
            ClassCard(clase)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassCard(clase: Clase = Clase()){
    Card(modifier = Modifier //Especificaciones para visibilidad de carta
        .clip(RoundedCornerShape(3.dp))
        .fillMaxWidth(),
        onClick = {}) {
        Image(painter = painterResource(id = clase.Portada),
            contentDescription = "Portada ilustrativa",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)))
        Text(text = clase.Nombre, fontSize = 15.sp,
            modifier=Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold)
    }
}