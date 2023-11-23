package com.example.proyecto_1.ui.historial.viewmodel

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyecto_1.Networking.Realtime_Manager
import java.util.HashMap

@Preview
@Composable
fun recoverQuestionsH(userID: String="0", classID: String="7657", quizID: String= "1", themeID: String = "35"): List<HashMap<String?, Any?>> {
    val realtime = Realtime_Manager()
    var preguntas = listOf<HashMap<String?, Any?>>()
    val referenceParcial = realtime.databaseReference.child(userID).child("Clases").child(classID).child("Parciales")
    val referencePreguntas = referenceParcial.child(quizID).child("Temas").child(themeID).child("Preguntas")
    referencePreguntas.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val dataSnapshot = task.result // Obtiene el resultado del get()
                if (dataSnapshot != null && dataSnapshot.exists()) {
                    // Los datos existen en el snapshot
                    val data = dataSnapshot.value // Datos en firestore
                    val data2 = data as HashMap<String?, HashMap<String?, Any?>>
                    preguntas = data2.values.toList()
                } else {
                    // No hay datos en la ubicación especificada
                    Log.d("TAG", "No data found")
                }
            } else {
                // La operación de escritura falló
                // Maneja el fallo aquí
                val exception = task.exception
                Log.e("TAG", "Error writing to database", exception)
            }
    }
    return preguntas
}