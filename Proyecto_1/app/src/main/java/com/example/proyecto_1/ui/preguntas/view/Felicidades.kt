package com.example.proyecto_1.ui.preguntas.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_1.R

@Preview
@Composable
fun Felicidades(){
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.felicitaciones),
            contentDescription = "books decoration",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Button(onClick = {
            //REGRESAR
        }, modifier = Modifier
            .align(Alignment.TopStart)
            .padding(top = 16.dp, start = 16.dp)) {
            Text(text = "Regresar")
        }

    }
}