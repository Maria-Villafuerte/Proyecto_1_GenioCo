package com.example.proyecto_1.ui.preguntas.viewmodel

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyecto_1.Networking.Realtime_Manager
import java.util.HashMap

@Preview
@Composable
fun recoverQuestions(userID: String="0", classID: String="7657", quizID: String= "1", themeID: String = "35"): List<HashMap<String?, Any?>> {
    val realtime = Realtime_Manager()
    var preguntas = listOf<HashMap<String?, Any?>>()
    val referenceParcial = realtime.databaseReference.child(userID).child("Clases").child(classID).child("Parciales")
    val referencePreguntas = referenceParcial.child(quizID).child("Temas").child(themeID).child("Preguntas")
    referencePreguntas.get().addOnSuccessListener {
        val data = it.value as HashMap<String, HashMap<String?, Any?>>
        preguntas = data.values.toList()
    }.addOnFailureListener {
        Log.e("firebase", "Error getting data", it)
    }
    return preguntas
}

