package com.example.proyecto_1.ui.historial.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_1.R
import com.example.proyecto_1.models.Questions
import com.example.proyecto_1.navigation.AppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AnsweredQuestions(navController: NavController = rememberNavController(), userID: String="", classID: String="", themeID: String = ""){
    val defaultQ = Questions()
    val allQuestions = remember { mutableStateListOf(
        defaultQ, defaultQ, defaultQ, defaultQ
    ) }
    Scaffold(
        topBar = { AppBar(stringResource(R.string.titulo_hisotry), navController= navController, userID= userID) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* acción al hacer clic en el botón */ }) {
                Icon(Icons.Rounded.Add, contentDescription = "Localized description")
            }
        },
        floatingActionButtonPosition = FabPosition.End, // opcional, puedes ajustar la posición según tus necesidades

    ) {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))){
            TopAppBar(title = { Text(stringResource(R.string.titulo_hisotry))})
            LazyVerticalGrid(columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))){
                items(allQuestions) {pregunta ->
                    Card (modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(dimensionResource(R.dimen.padding_small))){
                        Text(text = pregunta.pregunta + "\n" + pregunta.respuesta,
                            fontSize = 12.sp, fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(dimensionResource(R.dimen.padding_small)))
                    }
                }
            }
        }
    }
}