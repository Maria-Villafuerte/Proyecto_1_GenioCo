package com.example.proyecto_1.ui.preguntas.view

import android.annotation.SuppressLint
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_1.R
import com.example.proyecto_1.models.Questions
import com.example.proyecto_1.ui.theme.Blue
import com.example.proyecto_1.ui.theme.Bluetone
import com.example.proyecto_1.ui.theme.Green
import com.example.proyecto_1.ui.theme.Grey
import com.example.proyecto_1.ui.theme.Red
import com.example.proyecto_1.ui.theme.Yellow

val Preguntas_guardadas = arrayListOf(Questions( //Prueba
    "1","Matematicas", "¿Cuál es la derivada de x^2 con respecto de x?",
    "2x",
    listOf(
        "2x", "x", "2", "1"
    )), Questions("2","Lenguaje", "¿Cómo se escribe?",
    "Vaca",
    listOf(
        "Baca", "Vaca", "Ninguna de las anteriores"
    )))

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Preguntas(navController: NavController = rememberNavController(),
              userID: String="", classID: String="", themeID: String = "", preguntaID: String = "0") {
    var pregunta = Questions()
    if (preguntaID.toInt() > Preguntas_guardadas.size){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.fondo),
                contentDescription = "books decoration",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )
            Text(textAlign = TextAlign.Center,
                text=stringResource(R.string.completado),
                fontSize = 35.sp, fontWeight = FontWeight.ExtraBold,
                color = Bluetone)

            // Aparecer por medio segundo y transicionar a la siguiente pantalla
            LaunchedEffect(Unit) {
                delay(500) // Retraso de 500 milisegundos (0.5 segundos)
                navController.navigate(route = "HomeScreen/$userID")
            }
        }
    }else {
        for (q in Preguntas_guardadas) {
            if (preguntaID == q.id) {
                pregunta = q
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.height_profileBack))
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
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.height_question))
                    .padding(start = dimensionResource(R.dimen.padding_XXbig), end = dimensionResource(R.dimen.padding_XXbig),
                        top = dimensionResource(R.dimen.padding_big), bottom = dimensionResource(R.dimen.padding_big))
                    .background(Grey)
            ) {
                TextButton(content = { Text(text = pregunta.pregunta) },
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(start = dimensionResource(R.dimen.padding_small),
                            end = dimensionResource(R.dimen.padding_small)),
                    onClick = { navController.navigate("felicidad/$userID/$classID/$themeID/$preguntaID") })

            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
            Column {
                pregunta.opciones.take(4).forEachIndexed { index, opciones ->
                    val colores = listOf(Red, Blue, Yellow, Green)
                    Box(
                        modifier = Modifier
                            .height(dimensionResource(R.dimen.padding_XXbig))
                            .fillMaxSize()
                            .background(colores[index])
                            .padding(start = dimensionResource(R.dimen.padding_XXbig), end = dimensionResource(R.dimen.padding_XXbig))
                            .clickable {
                                if (opciones == pregunta.respuesta) {
                                    navController.navigate("felicidad/$userID/$classID/$themeID/$preguntaID")
                                } else {
                                    navController.navigate("Derrota/$userID/$classID/$themeID/$preguntaID")
                                }
                            }
                    ) {
                        Text(
                            opciones, modifier = Modifier
                                .align(Alignment.Center)
                                .padding(start = dimensionResource(R.dimen.padding_small), end = dimensionResource(R.dimen.padding_small)),
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_medium)))
                }
            }
        }
    }
}
