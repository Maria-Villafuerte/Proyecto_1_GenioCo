package com.example.proyecto_1.ui.materia.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.End
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_1.Networking.Realtime_Manager
import com.example.proyecto_1.R
import com.example.proyecto_1.models.Clase
import com.example.proyecto_1.models.User
import com.example.proyecto_1.navigation.AppBar



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pop_up(stro: Boolean,userID: String ) {
   val real = Realtime_Manager()
    var name by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }
    var pueba by remember { mutableStateOf(true) }
    if (pueba) {
        AlertDialog(
            onDismissRequest = {
                pueba = false
            },
            title = { Text(text = "Agregar una clase") },
            text = {
                Text("Bienvenido a mi aplicación.")
            },
            confirmButton = {
                val context = LocalContext.current
                Column() {

                    OutlinedTextField(
                        value = name,
                        onValueChange = {newText -> name = newText},
                        label = {Text("Nombre Usuario") },
                        modifier = Modifier
                        //    .width(dimensionResource(R.dimen.height_button))
                        //    .height(dimensionResource(R.dimen.padding_Xsmall))

                    )

                    OutlinedTextField(
                        value = id,
                        onValueChange = {newText -> id = newText},
                        label = {Text(stringResource(R.string.correo)) },
                        modifier = Modifier
                        //    .height(dimensionResource(R.dimen.padding_Xsmall))
                          //  .width(dimensionResource(R.dimen.height_button))
                    )
                    TextButton(
                        onClick = {
                            var usairo_nuevo: Clase = Clase(id,name)
                            pueba = false
                            real.agregar_clase(userID,usairo_nuevo)
                        },
                        modifier = Modifier
                            .padding(top = 10.dp)
                        // .width(dimensionResource(R.dimen.height_button))
                        //  .height(dimensionResource(R.dimen.padding_Xsmall))


                    ) {
                        Text("Agregar", color = Color.Gray)
                    }
                }
            }
        )

        var new_clase: Clase = Clase(id,name)
        real.agregar_clase(userID,new_clase)
    }
    else{
        Toast.makeText(LocalContext.current,"ASDA", Toast.LENGTH_SHORT).show()

    }


}

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController = rememberNavController(), userID: String = ""){
    val allClasses = remember { mutableStateListOf<Clase>(Clase()) }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { AppBar(stringResource(R.string.titulo_homescreen), navController= navController, userID= userID) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showDialog = true
                }) {
                Icon(Icons.Rounded.Add, contentDescription = "Localized description")
            }
        },
        floatingActionButtonPosition = FabPosition.End, // opcional, puedes ajustar la posición según tus necesidades
    ) {
        if (showDialog) {
            pop_up(showDialog,userID)
        }
        else {
            Toast.makeText(LocalContext.current,"dtttfg", Toast.LENGTH_SHORT).show()
        }
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