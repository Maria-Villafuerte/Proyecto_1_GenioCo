package com.example.proyecto_1.ui.preguntas.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_1.R

@Preview
@Composable
fun Felicidades(navController: NavController = rememberNavController(),
                userID: String="", classID: String="", quizID: String="", themeID:String = "", questionID: String = ""){
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.felicitaciones),
            contentDescription = "books decoration",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column ( modifier = Modifier.align(Alignment.Center)){
            Text(text = stringResource(R.string.felicidades),
                fontSize = 25.sp, fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Right, modifier = Modifier.width(dimensionResource(R.dimen.width_Ansbutton)))
            Text(text = stringResource(R.string.feli_support),
                fontSize = 15.sp, fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Right, modifier = Modifier.width(dimensionResource(R.dimen.width_Ansbutton)))
        }
        val pregunta = questionID.toInt() +1
        val questionID = pregunta.toString()
        Button(onClick = {
            navController.navigate(route = "Questions/$userID/$classID/$quizID/$themeID/$questionID") },
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = dimensionResource(R.dimen.padding_Xbig))) {
            Text(stringResource(R.string.siguiente))
        }
    }
}