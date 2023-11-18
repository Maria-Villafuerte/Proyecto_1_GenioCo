package com.example.proyecto_1.Networking

import android.widget.Toast
import com.example.proyecto_1.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Realtime_Manager {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("Usuarios")
    fun addContact(usuario: User) {
        val key = usuario.id
        if (key != null) {
            databaseReference.child(key).setValue(usuario)
        }
    }

    fun deleteContact(contactId: String) {
        databaseReference.child(contactId).removeValue()
    }

    fun get_total(): String {

        var total_datos =""
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // El recuento total de nodos bajo "Usuarios"
                val totalDatos = snapshot.childrenCount.toInt()
                total_datos = (totalDatos + 1).toString()
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error si ocurre
                println("Error al obtener el recuento: ${error.message}")
            }
        })
        return total_datos
    }
}