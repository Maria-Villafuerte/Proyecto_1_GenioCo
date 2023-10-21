package com.example.proyecto_1.ui.historial.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_1.models.Questions
import com.example.proyecto_1.ui.BottomBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AnsweredQuestions(){
    val defaultQ = Questions()
    val allQuestions = remember { mutableStateListOf(
        defaultQ, defaultQ, defaultQ, defaultQ
    ) }

    Scaffold( bottomBar = { BottomBar() }){
        Column(modifier = Modifier.padding(15.dp)){
            TopAppBar(title = { Text(text = "Contenido Estudiado")})
            LazyVerticalGrid(columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)){
                items(allQuestions) {pregunta ->
                    Box(modifier = Modifier
                        .background(Color.Gray)
                        .height(200.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Text(text = pregunta.materia, 
                            fontSize = 10.sp)
                        Text(text = pregunta.pregunta + "\n" + pregunta.respuesta)
                    }
                }
            }
        }
    }
}