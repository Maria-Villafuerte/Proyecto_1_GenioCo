package com.example.proyecto_1.ui.preguntas.viewmodel

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import com.example.proyecto_1.Networking.Realtime_Manager
import com.example.proyecto_1.models.Questions

@Preview
@Composable
fun recoverQuestions(userID: String="0", classID: String="7657", quizID: String= "1", themeID: String = "35"): List<Questions> {
    val preguntasLiveData = remember { MutableLiveData<List<Questions>>() }
    val realtime = Realtime_Manager()
    val referenceParcial = realtime.databaseReference.child(userID).child("Clases").child(classID).child("Parciales")
    val referencePreguntas = referenceParcial.child(quizID).child("Temas").child(themeID).child("Preguntas")

    // Obtener datos de Firebase
    referencePreguntas.get().addOnSuccessListener { dataSnapshot ->
        val preguntas = mutableListOf<Questions>()
        dataSnapshot.children.forEach { snapshot ->
            val id = snapshot.child("id").value.toString()
            val respuesta = snapshot.child("respuesta").value.toString()
            val pregunta0 = snapshot.child("pregunta").value.toString()

            val opcion1 = snapshot.child("opciones").child("0").value.toString()
            val opcion2 = snapshot.child("opciones").child("1").value.toString()
            val opcion3 = snapshot.child("opciones").child("2").value.toString()
            val opcion4 = snapshot.child("opciones").child("3").value.toString()
            val opciones = listOf(opcion1, opcion2, opcion3, opcion4)

            val pregunta = Questions(id, "", pregunta0, respuesta, opciones)
            preguntas.add(pregunta)
        }
        preguntasLiveData.value = preguntas
    }.addOnFailureListener { exception ->
        Log.e("firebase", "Error getting data", exception)
    }

    // Observar el LiveData y usar los datos en la UI
    val allQuestions by preguntasLiveData.observeAsState(emptyList())
    return  allQuestions
}

