package com.example.proyecto_1.ui.perfil.viewmodel

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyecto_1.Networking.Realtime_Manager
import com.example.proyecto_1.models.User
import java.util.HashMap

@Preview
@Composable
fun recoverUser(userID: String = "0"): User {
    val realtime = Realtime_Manager()
    var actualUser = User()
    realtime.databaseReference.child(userID).get().addOnSuccessListener {
        val data = it.value as HashMap<String, Any?>
        val name = data["name"].toString()
        val mail = data["mail"].toString()
        val password = data["password"].toString()
        actualUser = User(userID, name, mail,password)
    }.addOnFailureListener {
        Log.e("firebase", "Error getting data", it)
    }
    return actualUser
}
