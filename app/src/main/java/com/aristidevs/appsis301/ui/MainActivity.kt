package com.aristidevs.appsis301.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

//import android.widget.TextView
import android.content.Intent
import android.util.Log
import com.aristidevs.appsis301.R
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore


//import android.widget.Button
//import com.aristidevs.appsis301.util.PreferenceHelper
//import com.aristidevs.appsis301.util.PreferenceHelper.get
//import com.aristidevs.appsis301.util.PreferenceHelper.set

class MainActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //buttom para iniciar y que vaya a login
        val btn_Iniciar = findViewById<Button>(R.id.buttonIniciar) as Button


        btn_Iniciar.setOnClickListener(){
            //val iniciar = Intent(this, AuthActivity::class.java)
            val iniciar = Intent(this, MenuActivity::class.java)
            startActivity(iniciar)


        }

        val analitycs = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message","comenzando app")

        analitycs.logEvent("MainActivity",bundle)


        //agregarDatos()

    }

//    private  fun agregarDatos(){
//        val user = hashMapOf(
//            "first" to "juan",
//            "last" to "vargas",
//            "born" to 2000
//        )
//        //primer metodo
//        db.collection("usuarios")
//            .add(user)
//            .addOnSuccessListener { documentReference ->
//                Log.d("TAG", documentReference.id)
//            }
//            .addOnFailureListener { e ->
//                Log.w("TAG", "error $e")
//            }
//
//        //segundo metodo
//        db.collection("usuarios").document()
//            .set(user)
//            .addOnSuccessListener { Log.d("TAG", "Se guardo correctamente") }
//            .addOnFailureListener { e -> Log.w("TAG", "error $e") }
//
//        //tercer metodo
//        db.collection("usuarios").document("noman")
//            .set(user)
//            .addOnSuccessListener { Log.d("TAG", "Se guardo correctamente") }
//            .addOnFailureListener { e -> Log.w("TAG", "error $e") }
//    }

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
//                Log.d("TAG", documentReference.id)
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




