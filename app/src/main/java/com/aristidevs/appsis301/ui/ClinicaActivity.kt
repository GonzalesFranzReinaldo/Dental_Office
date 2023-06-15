package com.aristidevs.appsis301.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.aristidevs.appsis301.R
import com.google.firebase.firestore.FirebaseFirestore

class ClinicaActivity : AppCompatActivity() {

    //val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clinica)


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
}