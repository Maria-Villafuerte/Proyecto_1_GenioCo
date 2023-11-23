package com.example.proyecto_1.ui.temas.viewmodel

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import com.example.proyecto_1.Networking.Realtime_Manager
import com.example.proyecto_1.R
import com.example.proyecto_1.models.Parciales
import com.example.proyecto_1.models.Questions
import com.example.proyecto_1.models.Temas

@Preview
@Composable
fun recoverQuizzes(userID: String="0", classID: String="7657"): List<Parciales> {
    val parcialesLiveData = remember { MutableLiveData<List<Parciales>>() }
    val realtime = Realtime_Manager()
    val referenceParcial = realtime.databaseReference.child(userID).child("Clases").child(classID).child("Parciales")

    // Obtener datos de Firebase
    referenceParcial.get().addOnSuccessListener { dataSnapshot ->
        val parciales = mutableListOf<Parciales>()
        dataSnapshot.children.forEach { snapshot ->
            val id = snapshot.child("id").value.toString()
            val nombre = snapshot.child("nombre").value.toString()
            val temas: List<Temas> = listOf()
            val parcial = Parciales(id, nombre, temas)
            parciales.add(parcial)
        }
        parcialesLiveData.value = parciales
    }.addOnFailureListener { exception ->
        Log.e("firebase", "Error getting data", exception)
    }

    // Observar el LiveData y usar los datos en la UI
    val allparciales by parcialesLiveData.observeAsState(emptyList())
    return allparciales
}

@Preview
@Composable
fun recoverTopics(userID: String="0", classID: String="7657", quizID: String= "1"): List<Temas>? {
    val temasLiveData = remember { MutableLiveData<List<Temas>>() }
    val realtime = Realtime_Manager()
    val referenceParcial = realtime.databaseReference.child(userID).child("Clases").child(classID).child("Parciales")
    val referenceTemas = referenceParcial.child(quizID).child("Temas")

    // Obtener datos de Firebase
    referenceTemas.get().addOnSuccessListener { dataSnapshot ->
        val temas = mutableListOf<Temas>()
        dataSnapshot.children.forEach { snapshot ->
            val id = snapshot.child("id").value.toString()
            val nombre = snapshot.child("nombre").value.toString()
            val portada = R.drawable.portada2_clase
            val preguntas: List<Questions> = listOf()
            val tema = Temas(id, nombre, portada, preguntas)
            temas.add(tema)
        }
        temasLiveData.value = temas
    }.addOnFailureListener { exception ->
        Log.e("firebase", "Error getting data", exception)
    }

    val allTemas by temasLiveData.observeAsState(emptyList())
    return allTemas
}

