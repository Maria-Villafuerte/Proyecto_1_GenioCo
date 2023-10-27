package com.example.proyecto_1.ui.perfil.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_1.models.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable //Componente de ProfilePage
fun Editable(icon: ImageVector, text: String){
    ListItem( //Creación de elemento para lista
        headlineText = { Text(text = text, fontSize = 15.sp) }, //Texto titular
        trailingContent = { //Elemento a la derecha del texto
            IconButton(onClick = { }) {
                Icon(Icons.Outlined.KeyboardArrowRight,  contentDescription = null) } },
        //Elemento a la izquierda del texto
        leadingContent = { Icon(icon, contentDescription = null) }
    )
}
@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(actualUser: User = User()){
    Column(verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)){
            Image(painter = painterResource(id = actualUser.profileBack),
                contentDescription = "Profile back",
                contentScale = ContentScale.Crop, // Recorta la imagen,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp))
            Image(painter = painterResource(id = actualUser.profilePicture),
                contentDescription = "profile picture",
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.BottomCenter) //Ubicación al centro y final
                    .clip(CircleShape)
            )
            Text(text = "Nombre de usuario", color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomCenter) //Ubicación al centro y final
                    .padding(bottom = 20.dp), //Espaciado
                fontSize = 20.sp, fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold)
        }
        //Elementos en lista
        Editable(icon = Icons.Outlined.Person, text = "Edit Profile")
        Editable(icon = Icons.Outlined.Lock, text = "Reset Password")
        ListItem( //Elemento especial, contiene un botón switch en lugar de botón normal
            headlineText = { Text(text = "Notifications", fontSize = 15.sp) },
            trailingContent = {
                var checked by remember { mutableStateOf(true) }
                Switch(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                    }
                )
            },
            leadingContent = { Icon(Icons.Outlined.Notifications, contentDescription = null) }
        )
        Editable(icon = Icons.Outlined.Edit, text = "Clases")
    }
}