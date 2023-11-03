package com.example.proyecto_1.ui.selection.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_1.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_1.navigation.AppBar
import com.example.proyecto_1.navigation.NavigationState

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Selection(navController: NavController = rememberNavController(), themeID: String){

    val blueTone = Color(android.graphics.Color.parseColor("#5668a3"))
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {
        AppBar(title = "", navController = navController)
        Text(modifier = Modifier.padding(30.dp), fontWeight = FontWeight.Bold,
            text = stringResource(R.string.selesction),
            textAlign = TextAlign.Center,
            fontSize = 30.sp, color = blueTone)
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { navController.navigate(route = NavigationState.History.route) },
                modifier = Modifier
                    .width(300.dp)
                    .padding(10.dp),
                contentPadding = PaddingValues(20.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(blueTone)) {
            Image(painter = painterResource(id = R.mipmap.ic_students_foreground),
                contentDescription = "Students ilustration",
                modifier = Modifier.size(100.dp))
            Text(text = "\t"+ stringResource(R.string.hisotrial), textAlign = TextAlign.Center)
        }
        Button(onClick = {navController.navigate(route = "Preguntas/0")},
            modifier = Modifier
                .width(300.dp)
                .padding(10.dp),
            contentPadding = PaddingValues(20.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(blueTone)){
            Image(painter = painterResource(id = R.mipmap.ic_teachers_foreground),
                contentDescription = "Teachers ilustration",
                modifier = Modifier.size(100.dp))
            Text(text = "\t" + stringResource(R.string.exam))
        }
    }
}