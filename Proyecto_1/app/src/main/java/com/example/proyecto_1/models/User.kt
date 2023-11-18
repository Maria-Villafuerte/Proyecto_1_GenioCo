package com.example.proyecto_1.models

import com.example.proyecto_1.R

val clase: Clase=Clase("3024", "Arte")
data class User(var id:String = "0",
                var name: String = "Usuario000",
                var mail: String = "blabla@gmail.com",
                var password: String = "1234",
                var profilePicture: Int = R.mipmap.ic_students_foreground,
                var profileBack: Int = R.drawable.profile_back,
                var Clases: List<Clase> = listOf(clase)
)