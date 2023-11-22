package com.example.proyecto_1.Networking

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.proyecto_1.R
import com.example.proyecto_1.models.Clase
import com.example.proyecto_1.models.Parciales
import com.example.proyecto_1.models.Questions
import com.example.proyecto_1.models.Temas
import com.example.proyecto_1.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun prueba_datos(){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var totaldatos by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally // Alinea los elementos a la izquierda
    ) {
        androidx.compose.material3.Text(
            stringResource(R.string.bienveida_2),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        androidx.compose.material3.Text(
            stringResource(R.string.datos_1),
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = name,
            onValueChange = {newText -> name = newText},
            label = { androidx.compose.material3.Text("Nombre Usuario") },
            modifier = Modifier
                .width(dimensionResource(R.dimen.width_textBox))
                .padding(dimensionResource(R.dimen.padding_Xsmall))
        )

        OutlinedTextField(
            value = email,
            onValueChange = {newText -> email = newText},
            label = { androidx.compose.material3.Text(stringResource(R.string.correo)) },
            modifier = Modifier
                .width(dimensionResource(R.dimen.width_textBox))
                .padding(dimensionResource(R.dimen.padding_Xsmall))
        )

        OutlinedTextField(
            value = password,
            onValueChange = { newText -> password = newText },
            label = { androidx.compose.material3.Text( stringResource(R.string.contra)) },
            modifier = Modifier
                .width(dimensionResource(R.dimen.width_textBox))
                .padding(dimensionResource(R.dimen.padding_Xsmall))
        )
        TextButton(onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.End)
                .height(dimensionResource(R.dimen.padding_XXbig)))
        { androidx.compose.material3.Text(stringResource(R.string.contra_1)) }
        val context = LocalContext.current
        val openAlertDialog = remember { mutableStateOf(false) }
        val realtime = Realtime_Manager()

        realtime.databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // El recuento total de nodos bajo "Usuarios"
                val totalDatos = snapshot.childrenCount.toInt()
                totaldatos = (totalDatos + 1).toString()
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error si ocurre
                println("Error al obtener el recuento: ${error.message}")
            }
        })
        val savedUsertext = stringResource(R.string.registrado)
        val nodata = stringResource(R.string.noData)
        androidx.compose.material3.Button(
            onClick = {
                val usuario = User(id = totaldatos, name = name, mail = email, password = password)
                if (usuario.name != "") {
                    realtime.addContact(usuario)
                    Toast.makeText(context, savedUsertext, Toast.LENGTH_SHORT).show()
                   /* navController.navigate("welcome_login/$userID")*/
                } else {
                    Toast.makeText(context, nodata, Toast.LENGTH_SHORT).show()
                }
                totaldatos = ""
                email = ""
                password = ""
                name = ""
                openAlertDialog.value = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.padding_XXXbig))
                .width(dimensionResource(R.dimen.width_textBox))
                .padding(dimensionResource(R.dimen.padding_Xsmall))
        ) {
            androidx.compose.material3.Text(stringResource(R.string.registro))
        }
        TextButton(onClick = {/*navController.navigate(route = NavigationState.Login.route) */},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(dimensionResource(R.dimen.padding_XXbig)))
        { androidx.compose.material3.Text(stringResource(R.string.cambio_inicio)) }

    }
}

@Preview
@Composable
fun prenas (){
    var uruario_nuevo: User = User ("0", "Pancho Guerra","pasdaso", "asdasd")
    var clase_nueva: Clase= Clase("7657", "Teatro")
    var Parcial_nuevo: Parciales= Parciales("1", "Parcial 1")
    var tema_nuevo: Temas= Temas("35", "La hisotria del arte")
    val opciones: List<String> = listOf("1998", "1785", "1965", "1240")
    var pregutna_nueva: Questions= Questions("10", "","¿En que año se hizo la monalisa?", "1785",opciones)




    val rieal=Realtime_Manager ()

   rieal.addContact(uruario_nuevo)
   //rieal.agregar_clase(uruario_nuevo,clase_nueva)
  // rieal.agregar_parcial(uruario_nuevo,clase_nueva,Parcial_nuevo)
  // rieal.agregar_tema(uruario_nuevo,clase_nueva,Parcial_nuevo,tema_nuevo)
   //rieal.agregar_pregunta(uruario_nuevo,clase_nueva,Parcial_nuevo,tema_nuevo,pregutna_nueva)
}