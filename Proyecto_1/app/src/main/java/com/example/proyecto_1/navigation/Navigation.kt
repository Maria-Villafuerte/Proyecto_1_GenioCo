package com.example.proyecto_1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyecto_1.ui.bienvenida.view.WelcomeScreen
import com.example.proyecto_1.ui.bienvenida.view.WelcomeLoginScreen
import com.example.proyecto_1.ui.login.view.LoginScreen
import com.example.proyecto_1.ui.materia.view.HomeScreen
import com.example.proyecto_1.ui.preguntas.view.Felicidades

import com.example.proyecto_1.ui.selection.view.Selection
import com.example.proyecto_1.ui.historial.view.AnsweredQuestions
import com.example.proyecto_1.ui.perfil.view.ProfilePage
import com.example.proyecto_1.ui.preguntas.view.Derrota
import com.example.proyecto_1.ui.preguntas.view.Preguntas
import com.example.proyecto_1.ui.register.view.RegisterScreen
import com.example.proyecto_1.ui.temas.view.Temas_Clases

@Composable
fun Navigation_confi() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = NavigationState.Welcome.route) {
        //Elementos sin parámetros
        composable(route = NavigationState.Welcome.route) {//Bienvenida
            WelcomeScreen(navController)
        }
        composable(route = NavigationState.Login.route) {//Inicio de sesión
            LoginScreen(navController)
        }
        composable(route = NavigationState.Register.route){//Registro
            RegisterScreen(navController)
        }

        //Elementos que necesitan el paso de información
        composable( //BIENVENIDA USUARIO
            route= NavigationState.WelcomeLogin.route,
            arguments = listOf(navArgument("userID"){type = NavType.StringType})
        ){
            val userID = it.arguments?.getString("userID")
            if (userID != null) WelcomeLoginScreen(navController, userID)
        }
        composable( //HOME
            route= NavigationState.Home.route,
            arguments = listOf(navArgument("userID"){type = NavType.StringType})
        ){
            val userID = it.arguments?.getString("userID")
            if (userID != null) HomeScreen(navController, userID)
        }
        composable( //PERFIL
            route= NavigationState.ProfilePage.route,
            arguments = listOf(navArgument("userID"){type = NavType.StringType})
        ){
            val userID = it.arguments?.getString("userID")
            if (userID != null) ProfilePage(navController, userID)
        }
        composable( //TEMAS
            route= NavigationState.Topics.route,
            arguments = listOf(
                navArgument("userID"){type = NavType.StringType},
                navArgument("classID"){type = NavType.StringType})
        ){
            val userID = it.arguments?.getString("userID")
            val classID = it.arguments?.getString("classID")
            if (userID != null) {
                if (classID != null) {
                    Temas_Clases(navController, userID, classID)
                }
            }
        }
        composable( //ELECCIÓN (historial o quiz)
            route= NavigationState.Selection.route,
            arguments = listOf(
                navArgument("userID"){type = NavType.StringType},
                navArgument("classID"){type = NavType.StringType},
                navArgument("themeID"){type = NavType.StringType})
        ){
            val userID = it.arguments?.getString("userID")
            val classID = it.arguments?.getString("classID")
            val themeID = it.arguments?.getString("themeID")
            if (userID != null) {
                if (classID != null) {
                    if (themeID != null) {
                        Selection(navController, userID, classID, themeID)
                    }
                }
            }
        }
        composable( //HISTORIAL
            route= NavigationState.History.route,
            arguments = listOf(
                navArgument("userID"){type = NavType.StringType},
                navArgument("classID"){type = NavType.StringType},
                navArgument("themeID"){type = NavType.StringType})
        ){
            val userID = it.arguments?.getString("userID")
            val classID = it.arguments?.getString("classID")
            val themeID = it.arguments?.getString("themeID")
            if (userID != null) {
                if (classID != null) {
                    if (themeID != null) {
                        AnsweredQuestions(navController, userID, classID, themeID)
                    }
                }
            }
        }
        composable( //PREGUNTAS
                route=NavigationState.Questions.route,
                arguments = listOf(
                    navArgument("userID"){type = NavType.StringType},
                    navArgument("classID"){type = NavType.StringType},
                    navArgument("themeID"){type = NavType.StringType},
                    navArgument("questionID"){type = NavType.StringType})
        ){
            val userID = it.arguments?.getString("userID")
            val classID = it.arguments?.getString("classID")
            val themeID = it.arguments?.getString("themeID")
            val questionID = it.arguments?.getString("questionID")
            if (userID != null) {
                if (classID != null) {
                    if (themeID != null) {
                        if (questionID != null) {
                            Preguntas(navController, userID, classID, themeID, questionID)
                        }
                    }
                }
            }
        }
        composable(route = NavigationState.Congrats.route,
            arguments = listOf(
                    navArgument("userID"){type = NavType.StringType},
                    navArgument("classID"){type = NavType.StringType},
                    navArgument("themeID"){type = NavType.StringType},
                    navArgument("questionID"){type = NavType.StringType})
        ){//Pregunta correcta
                val userID = it.arguments?.getString("userID")
                val classID = it.arguments?.getString("classID")
                val themeID = it.arguments?.getString("themeID")
                val questionID = it.arguments?.getString("questionID")
                if (userID != null) {
                    if (classID != null) {
                        if (themeID != null) {
                            if (questionID != null) {
                                Felicidades(navController, userID, classID, themeID, questionID)
                            }
        }}}}
        composable(route = NavigationState.Fail.route,
            arguments = listOf(
                navArgument("userID"){type = NavType.StringType},
                navArgument("classID"){type = NavType.StringType},
                navArgument("themeID"){type = NavType.StringType},
                navArgument("questionID"){type = NavType.StringType})
        ){//Pregunta incorrecta
            val userID = it.arguments?.getString("userID")
            val classID = it.arguments?.getString("classID")
            val themeID = it.arguments?.getString("themeID")
            val questionID = it.arguments?.getString("questionID")
            if (userID != null) {
                if (classID != null) {
                    if (themeID != null) {
                        if (questionID != null) {
                            Derrota(navController, userID, classID, themeID, questionID)
        }}}}}
    }
}

/**
 * --> manda
 * __recibe__
 *
 * Inicio de sesion --> idUsuario
 * __idUsuario__ homescreen --> idClase
 * __idClase__ temas --> idTema
 * __idTema__ selection --> idTema
 * __idTema__ preguntas (hace un loop usando idPregunta)
 */