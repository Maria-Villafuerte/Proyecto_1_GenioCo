package com.example.proyecto_1.models

import com.example.proyecto_1.R

data class Temas(var id:String = "0",
                 var Nombre:String = "Nombre Tema",
                 var Portada:Int = R.drawable.portada_clase,
                 val Preguntas: List<Questions> = listOf())
