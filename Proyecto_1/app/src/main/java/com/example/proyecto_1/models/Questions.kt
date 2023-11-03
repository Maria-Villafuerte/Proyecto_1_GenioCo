package com.example.proyecto_1.models

data class Questions( val id:String = "0",
                val materia:String = "Nombre de clase",
                val pregunta:String = "¿?",
                val respuesta:String = "",
                val opciones: List<String> = listOf())
