package com.example.proyecto_1.ui.preguntas.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_1.R
import com.example.proyecto_1.models.Questions
import com.example.proyecto_1.navigation.NavigationState
import com.example.proyecto_1.ui.theme.Blue
import com.example.proyecto_1.ui.theme.Green
import com.example.proyecto_1.ui.theme.Grey
import com.example.proyecto_1.ui.theme.Red
import com.example.proyecto_1.ui.theme.Yellow

val Preguntas_guardadas = arrayListOf(Questions( //Prueba
    "1","Matematicas", "¿Cuál es la derivada de x^2 con respecto de x?",
    "2x",
    listOf(
        "2x", "x", "2", "1"
    )), Questions("2","Lenguaje", "¿Cuál es la derivada de x^2 con respecto de x",
    "2x",
    listOf(
        "2x", "x", "2", "1"
    )))

@Composable
fun Preguntas(navController: NavController = rememberNavController(), preguntaID: String = "0") {
    var pregunta = Questions()
    for (q in Preguntas_guardadas) {
        if (preguntaID == q.id) {
            pregunta = q
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.Blue)
        ) {
            Image(
                painter = painterResource(id = R.drawable.portada2_clase),
                contentDescription = "Portada ilustrativa",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(start = 40.dp, end = 40.dp, top = 20.dp, bottom = 20.dp)
                .background(Grey)
        ) {
            TextButton(content= {Text(text = pregunta.pregunta)},
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = 10.dp, end = 10.dp),
                onClick = { navController.navigate(NavigationState.Congrats.route)})

        }
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            pregunta.opciones.take(4).forEachIndexed { index, opciones ->
                val colores = listOf(Red, Blue, Yellow, Green)
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxSize()
                        .background(colores[index])
                        .padding(start = 40.dp, end = 40.dp)
                        .clickable {
                            var S = if (opciones==pregunta.respuesta) {
                                navController.navigate(route = NavigationState.Congrats.route)
                            } else {
                                navController.navigate(route = NavigationState.Fail.route)                            }
                        }
                ) {
                    Text(
                        opciones, modifier = Modifier
                            .align(Alignment.Center)
                            .padding(start = 10.dp, end = 10.dp),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.size(15.dp))
            }
        }
    }
}


@Preview
@Composable
fun Crear_preguntas() {
    Preguntas(preguntaID = "1")
}