package com.example.proyecto_1.models

import com.example.proyecto_1.R

data class Parciales( var id:String = "0",
                    var Nombre:String = "Nombre Clase",
                    val temas: List<Temas> = listOf())

