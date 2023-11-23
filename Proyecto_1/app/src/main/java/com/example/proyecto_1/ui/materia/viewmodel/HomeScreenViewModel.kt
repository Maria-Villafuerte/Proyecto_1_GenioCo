package com.example.proyecto_1.ui.materia.viewmodel

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import com.example.proyecto_1.Networking.Realtime_Manager
import com.example.proyecto_1.R
import com.example.proyecto_1.models.Clase
import com.example.proyecto_1.models.Parciales

@Composable
fun recoverClasses(userID: String = "0"): List<Clase> {
    val classesLiveData = remember { MutableLiveData<List<Clase>>() }
    val realtime = Realtime_Manager()
    val reference = realtime.databaseReference.child(userID).child("Clases")
    // Obtener datos de Firebase
    reference.get().addOnSuccessListener { dataSnapshot ->
        val classes = mutableListOf<Clase>()
        dataSnapshot.children.forEach { snapshot ->
            val id = snapshot.child("id").value.toString()
            val nombre = snapshot.child("nombre").value.toString()
            val portada = R.drawable.portada_clase
            val parciales: List<Parciales> = listOf()
            val clase = Clase(id, nombre, portada, parciales)
            classes.add(clase)
        }
        classesLiveData.value = classes
    }.addOnFailureListener { exception ->
        Log.e("firebase", "Error getting data", exception)
    }

    // Observar el LiveData y usar los datos en la UI
    val allClasses by classesLiveData.observeAsState(emptyList())
    return allClasses
}