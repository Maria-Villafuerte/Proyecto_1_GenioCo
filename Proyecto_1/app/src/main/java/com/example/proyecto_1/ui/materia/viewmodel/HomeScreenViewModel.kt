package com.example.proyecto_1.ui.materia.viewmodel

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyecto_1.Networking.Realtime_Manager
import java.util.HashMap

@Preview
@Composable
fun recoverClasses(userID: String = "0"): List<HashMap<String?, Any?>> {
    val realtime = Realtime_Manager()
    var clases = listOf<HashMap<String?, Any?>>()
    realtime.databaseReference.child(userID).child("Clases").get().addOnSuccessListener {
        val data = it.value as HashMap<String, HashMap<String?, Any?>>
        clases = data.values.toList()
    }.addOnFailureListener {
        Log.e("firebase", "Error getting data", it)
    }
    return clases
}
