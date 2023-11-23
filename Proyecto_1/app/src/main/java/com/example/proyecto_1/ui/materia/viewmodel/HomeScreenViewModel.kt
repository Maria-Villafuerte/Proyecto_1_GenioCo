package com.example.proyecto_1.ui.materia.viewmodel

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.proyecto_1.Networking.Realtime_Manager
import com.example.proyecto_1.R
import com.example.proyecto_1.models.Clase
import com.example.proyecto_1.models.Parciales
import com.example.proyecto_1.models.User
import java.util.HashMap

fun recoverClasses(userID: String = "0", ): List<Clase> {
    val realtime = Realtime_Manager()
    val clases = mutableListOf<Clase>()
    val reference = realtime.databaseReference.child(userID).child("Clases")
    reference.get().addOnSuccessListener {
        val allData = it.value as HashMap<String, HashMap<String?, Any?>>
        val classesData = allData.values.toList()
        for (value in classesData){
            var id:String = value["id"].toString()
            var Nombre:String = value["nombre"].toString()
            var Portada:Int = R.drawable.portada_clase
            val parciales: List<Parciales> = listOf()
            val actualClass = Clase(id, Nombre, Portada, parciales)
            clases += actualClass
        }
    }.addOnFailureListener {
        Log.e("firebase", "Error getting data", it)
    }
    return clases
}