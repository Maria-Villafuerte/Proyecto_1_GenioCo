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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
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
import com.example.proyecto_1.ui.theme.Blue
import com.example.proyecto_1.ui.theme.Green
import com.example.proyecto_1.ui.theme.Grey
import com.example.proyecto_1.ui.theme.Pink40
import com.example.proyecto_1.ui.theme.Pink80
import com.example.proyecto_1.ui.theme.Purple80
import com.example.proyecto_1.ui.theme.PurpleGrey80
import com.example.proyecto_1.ui.theme.Red
import com.example.proyecto_1.ui.theme.Yellow


@Composable
fun Preguntas(pregunta: Questions = Questions(),navController: NavController = rememberNavController()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp,start = 16.dp, end = 16.dp)
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
            Text(text = pregunta.pregunta, modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 10.dp, end= 10.dp)
                , textAlign = TextAlign.Center
            )

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
                            //Función Nav de abajo
                        }
                ) {
                    Text(
                        opciones, modifier = Modifier
                            .align(Alignment.Center)
                            .padding(start = 10.dp, end= 10.dp),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.size(15.dp))
            }
        }
    }
}

@Composable
fun nav(opcion: String= String(), pregunta: Questions= Questions()){
     var S = if (opcion==pregunta.respuesta) {
        "Navegar a Bueno"
    } else {
        "Navegar a malo"
    }


}
@Preview
@Composable
fun crear_preguntas() {
    val Question_1 = Questions(
        "Matematicas", "¿Cuál es la derivada de x^2 con respecto de x",
        "2x",
        listOf(
            "2x", "x", "2", "1"
        )
    )
    Preguntas(Question_1)
}