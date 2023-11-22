package com.example.proyecto_1.Networking

import android.widget.Toast
import com.example.proyecto_1.models.Clase
import com.example.proyecto_1.models.Parciales
import com.example.proyecto_1.models.Questions
import com.example.proyecto_1.models.Temas
import com.example.proyecto_1.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Realtime_Manager (userID: String= ""){
    public val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("Usuarios")
    fun addContact1(usuario: User) {
        val key = usuario.id
        val clasesRef = databaseReference.child(usuario.id).child("Clases")
        val parcialesRef = clasesRef.child("Parciales")
        val temasRef = parcialesRef.child("Temas")
        clasesRef.setValue("clase")
        parcialesRef.setValue("parcial_temp")
        temasRef.setValue("temas_temp")

        if (key != null) {
            databaseReference.child(key).setValue(usuario)
        }
    }

    fun agregar_clase(usuario: String,clase: Clase){
        databaseReference.child(usuario).child("Clases").child(clase.id).setValue(clase)
    }

    fun agregar_parcial(usuario: String,clase: String,parcial: Parciales){
        databaseReference.child(usuario).child("Clases").child(clase).child("Parciales").child(parcial.id).setValue(parcial)
    }

    fun agregar_tema(usuario: String,clase: String,parcial: String, temas: Temas){
        databaseReference.child(usuario).child("Clases").child(clase).child("Parciales").child(parcial).child("Temas").child(temas.id).setValue(temas)
    }

    fun agregar_pregunta(usuario: String,clase: String,parcial: String, temas: String, pregunta:Questions){
        databaseReference.child(usuario).child("Clases").child(clase).child("Parciales").child(parcial).child("Temas").child(temas).child("Preguntas").child(pregunta.id).setValue(pregunta)
    }

    fun addContact(usuario: User,clase_a_agregar: Clase = Clase("",""),parcial_a_agregar: Parciales = Parciales("",""),tema_a_agregar: Temas = Temas("","")) {
        if (usuario.id != null) {
            databaseReference.child(usuario.id).setValue(usuario)
        }
    }

    fun deleteContact(contactId: String) {
        databaseReference.child(contactId).removeValue()
    }

    fun get_total(): String {

        var total_datos = ""
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

