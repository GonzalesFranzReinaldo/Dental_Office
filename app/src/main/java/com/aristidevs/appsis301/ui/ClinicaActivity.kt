package com.aristidevs.appsis301.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.aristidevs.appsis301.R
import com.google.firebase.firestore.FirebaseFirestore

class ClinicaActivity : AppCompatActivity() {

    //val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clinica)

        val btnmap= findViewById<Button>(R.id.btnvermapa) as Button

        btnmap.setOnClickListener() {

            val gmmIntentUri = Uri.parse("https://goo.gl/maps/eqPihs9Ap4fv4qHaA") // Cambia la URL según el enlace de Google Maps que deseas abrir
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
        }


        }



        //agregarDatos()
    }

//    private  fun agregarDatos(){
//        val doctor = hashMapOf(
//            "nombre" to "dr. Jose",
//            "apellido" to "vargas",
//            "especialidad" to "ortodoncia",
//            "correo" to "josevargas@gmail.com",
//            "telefono" to 72838232
//        )
//        //primer metodo
//        db.collection("doctores")
//            .add(doctor)
//            .addOnSuccessListener { documentReference ->
//               Log.d("TAG", documentReference.id)
//            }
//            .addOnFailureListener { e ->
//                Log.w("TAG", "error $e")
//            }
//        //segundo metodo
//        db.collection("doctores").document()
//            .set(doctor)
//            .addOnSuccessListener { Log.d("TAG", "Se guardo correctamente") }
//            .addOnFailureListener { e -> Log.w("TAG", "error $e") }
//
//        //tercer metodo
//        db.collection("doctores").document("name")
//            .set(doctor)
//            .addOnSuccessListener { Log.d("TAG", "Se guardo correctamente") }
//            .addOnFailureListener { e -> Log.w("TAG", "error $e") }
//    }
