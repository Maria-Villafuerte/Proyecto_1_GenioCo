package com.example.proyecto_1.models

import com.example.proyecto_1.R

data class User(var name: String = "Usuario000",
                var mail: String = "blabla@gmail.com",
                var password: String = "1234",
                var profilePicture: Int = R.mipmap.ic_students_foreground,
                var profileBack: Int = R.drawable.profile_back)