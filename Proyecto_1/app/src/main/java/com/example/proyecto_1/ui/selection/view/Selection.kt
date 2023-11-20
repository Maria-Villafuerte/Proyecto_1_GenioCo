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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
        topBar = { AppBar("", navController = navController, userID = userID) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(top = dimensionResource(R.dimen.width_Ansbutton))
        ) {
            Text(
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_XXbig)), fontWeight = FontWeight.Bold,
                text = stringResource(R.string.selection),
                textAlign = TextAlign.Center,
                fontSize = 30.sp, color = Bluetone
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
            Button(
                onClick = { navController.navigate(route = "historial/$userID/$classID/$themeID") },
                modifier = Modifier
                    .width(dimensionResource(R.dimen.height_profileBack))
                    .padding(dimensionResource(R.dimen.padding_small)),
                contentPadding = PaddingValues(dimensionResource(R.dimen.padding_big)),
                shape = RoundedCornerShape(dimensionResource(R.dimen.padding_big)),
                colors = ButtonDefaults.buttonColors(Bluetone)
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.ic_history_foreground),
                    contentDescription = "Books ilustration",
                    modifier = Modifier.size(dimensionResource(R.dimen.height_question))
                )
                Text(text = "\t" + stringResource(R.string.hisotrial), textAlign = TextAlign.Center)
            }
            Button(
                onClick = { navController.navigate(route = "Questions/$userID/$classID/$themeID/0") },
                modifier = Modifier
                    .width(dimensionResource(R.dimen.height_profileBack))
                    .padding(dimensionResource(R.dimen.padding_small)),
                contentPadding = PaddingValues(dimensionResource(R.dimen.padding_big)),
                shape = RoundedCornerShape(dimensionResource(R.dimen.padding_big)),
                colors = ButtonDefaults.buttonColors(Bluetone)
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.ic_question_foreground),
                    contentDescription = "Question ilustration",
                    modifier = Modifier.size(dimensionResource(R.dimen.height_question))
                )
                Text(text = "\t" + stringResource(R.string.exam))
            }
        }
    }
}