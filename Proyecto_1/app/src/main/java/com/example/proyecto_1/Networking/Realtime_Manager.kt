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

    fun agregar_clase(usuario: User,clase: Clase){
        databaseReference.child(usuario.id).child("Clases").child(clase.id).setValue(clase)
    }

    fun agregar_parcial(usuario: User,clase: Clase,parcial: Parciales){
        databaseReference.child(usuario.id).child("Clases").child(clase.id).child("Parciales").child(parcial.id).setValue(parcial)
    }

    fun agregar_tema(usuario: User,clase: Clase,parcial: Parciales, temas: Temas){
        databaseReference.child(usuario.id).child("Clases").child(clase.id).child("Parciales").child(parcial.id).child("Temas").child(temas.id).setValue(temas)
    }

    fun agregar_pregunta(usuario: User,clase: Clase,parcial: Parciales, temas: Temas, pregunta:Questions){
        databaseReference.child(usuario.id).child("Clases").child(clase.id).child("Parciales").child(parcial.id).child("Temas").child(temas.id).child("Preguntas").child(pregunta.id).setValue(pregunta)
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

/*
*
        // Guardar las clases (asumo que usuario.clases es una lista de Clase)
        usuario.Clases?.let { clasesList ->
            for (clase in clasesList) {
                var parcailes_null: List<Parciales> = listOf()
                parcailes_null += parcial_a_agregar
                val clase_preuba: Clase = Clase(clase.id, clase.Nombre, parciales = parcailes_null)
                databaseReference.child(usuario.id).child("Clases").child(clase.id)
                    .setValue(clase_preuba)


                //Guardar Parciales
                clase.parciales?.let { parcialesList ->
                    for (parcial in parcialesList) {
                        var temas_null = parcial.temas
                        temas_null += tema_a_agregar
                        val parcuales_preuba: Parciales = Parciales(parcial.id, parcial.Nombre,)
                        databaseReference.child(usuario.id).child("Clases").child(clase.id)
                            .child("Parciales").child(parcial.id).setValue(parcuales_preuba)


                        //Guardar temas
                        parcial.temas?.let { temasList ->
                            for (temas in temasList) {
                                var preguntas: List<Questions> = listOf()
                                val temas_prueba: Temas =
                                    Temas(temas.id, temas.Nombre, Preguntas = preguntas)
                                databaseReference.child(usuario.id).child("Clases").child(clase.id)
                                    .child("Parciales").child(parcial.id).child("Temas")
                                    .child(temas.id).setValue(temas_prueba)


                                //Guardar temas
                                temas.Preguntas?.let { preguntasList ->
                                    for (pregunta in preguntasList) {
                                        databaseReference.child(usuario.id).child("Clases")
                                            .child(clase.id).child("Parciales").child(parcial.id)
                                            .child("Temas").child(temas.id).setValue(pregunta)
                                    }
                                }
                            }
                        }
                    }


                }
            }
        }
*
* */
