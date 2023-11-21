package com.example.proyecto_1.ui.materia.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_1.R
import com.example.proyecto_1.models.Clase
import com.example.proyecto_1.navigation.AppBar

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController = rememberNavController(), userID: String = ""){
    val allClasses = remember { mutableStateListOf<Clase>(Clase()) }
    Scaffold(
        topBar = { AppBar(stringResource(R.string.titulo_homescreen), navController= navController, userID= userID) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* acción al hacer clic en el botón */ }) {
                Icon(Icons.Rounded.Add, contentDescription = "Localized description")
            }
        },
        floatingActionButtonPosition = FabPosition.End, // opcional, puedes ajustar la posición según tus necesidades
    ) {
        if(allClasses.isEmpty()){
            Box(modifier = Modifier.fillMaxSize()){
                Text(text = stringResource(id = R.string.clases),
                    modifier = Modifier
                        .align(Alignment.Center) //Ubicación al centro y final
                        .padding(bottom = dimensionResource(R.dimen.padding_big)), //Espaciado
                    fontSize = 20.sp, fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold)
            }
        } else{
            LazyColumn(verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_Xsmall)),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.padding_big))){
                item {
                    //Columna deslizable, con elementos generales de clases
                    TopAppBar(title = { Text(text = "")}) }
                items(allClasses){clase ->
                    ClassCard(navController, userID, clase)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassCard(navController: NavController, userID: String = "", clase: Clase = Clase()){
    Card(modifier = Modifier //Especificaciones para visibilidad de carta
        .clip(RoundedCornerShape(dimensionResource(R.dimen.roundClip)))
        .fillMaxWidth(),
        onClick = { navController.navigate(route = "Topics/$userID/" + clase.id)}) {
        Image(painter = painterResource(id = clase.Portada),
            contentDescription = "Portada ilustrativa",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(dimensionResource(R.dimen.padding_small))))
        Text(text = clase.Nombre, fontSize = 15.sp,
            modifier=Modifier.padding(dimensionResource(R.dimen.padding_small)),
            fontWeight = FontWeight.Bold)
    }
}