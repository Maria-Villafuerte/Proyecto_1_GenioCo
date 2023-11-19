package com.example.proyecto_1.ui.selection.view

import android.annotation.SuppressLint
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_1.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_1.navigation.AppBar
import com.example.proyecto_1.ui.theme.Bluetone

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Selection(navController: NavController = rememberNavController(), userID: String="", classID: String="", themeID: String = "") {
    Scaffold(
        topBar = {
            AppBar(
                stringResource(R.string.titulo_homescreen),
                navController = navController,
                userID = userID
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(top = 150.dp)
        ) {
            Text(
                modifier = Modifier.padding(30.dp), fontWeight = FontWeight.Bold,
                text = stringResource(R.string.selection),
                textAlign = TextAlign.Center,
                fontSize = 30.sp, color = Bluetone
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { navController.navigate(route = "historial/$userID/$classID/$themeID") },
                modifier = Modifier
                    .width(300.dp)
                    .padding(10.dp),
                contentPadding = PaddingValues(20.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(Bluetone)
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.ic_history_foreground),
                    contentDescription = "Books ilustration",
                    modifier = Modifier.size(100.dp)
                )
                Text(text = "\t" + stringResource(R.string.hisotrial), textAlign = TextAlign.Center)
            }
            Button(
                onClick = { navController.navigate(route = "Questions/$userID/$classID/$themeID/0") },
                modifier = Modifier
                    .width(300.dp)
                    .padding(10.dp),
                contentPadding = PaddingValues(20.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(Bluetone)
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.ic_question_foreground),
                    contentDescription = "Question ilustration",
                    modifier = Modifier.size(100.dp)
                )
                Text(text = "\t" + stringResource(R.string.exam))
            }
        }
    }
}