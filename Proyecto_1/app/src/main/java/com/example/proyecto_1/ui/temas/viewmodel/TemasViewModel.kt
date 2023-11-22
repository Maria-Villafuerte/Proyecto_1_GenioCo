package com.example.proyecto_1.ui.temas.viewmodel

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyecto_1.Networking.Realtime_Manager
import java.util.HashMap

@Preview
@Composable
fun recoverQuizzes(userID: String="0", classID: String="7657"): List<HashMap<String?, Any?>> {
    val realtime = Realtime_Manager()
    var parciales = listOf<HashMap<String?, Any?>>()
    val reference = realtime.databaseReference.child(userID).child("Clases").child(classID).child("Parciales")
    reference.get().addOnSuccessListener {
        val data = it.value as HashMap<String, HashMap<String?, Any?>>
        parciales = data.values.toList()
    }.addOnFailureListener {
        Log.e("firebase", "Error getting data", it)
    }
    return parciales
}

@Preview
@Composable
fun recoverTopics(userID: String="0", classID: String="7657", quizID: String= "1"): List<HashMap<String?, Any?>> {
    val realtime = Realtime_Manager()
    var temas = listOf<HashMap<String?, Any?>>()
    val referenceParcial = realtime.databaseReference.child(userID).child("Clases").child(classID).child("Parciales")
    val referenceTemas = referenceParcial.child(quizID).child("Temas")
    referenceTemas.get().addOnSuccessListener {
        val data = it.value as HashMap<String, HashMap<String?, Any?>>
        temas = data.values.toList()
    }.addOnFailureListener {
        Log.e("firebase", "Error getting data", it)
    }
    return temas
}

