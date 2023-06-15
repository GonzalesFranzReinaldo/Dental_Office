package com.aristidevs.appsis301.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aristidevs.appsis301.R
import com.aristidevs.appsis301.databinding.ActivityClinicaBinding
import com.aristidevs.appsis301.io.response.ItemUsu
import com.aristidevs.appsis301.model.AdapterDoctor

class ClinicaActivity : AppCompatActivity() {

    //val db = FirebaseFirestore.getInstance()
    private lateinit var binding: ActivityClinicaBinding
    private lateinit var adapterDoc: AdapterDoctor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClinicaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val btnmap= findViewById<Button>(R.id.btnvermapa) as Button

        btnmap.setOnClickListener() {

            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://goo.gl/maps/eqPihs9Ap4fv4qHaA")
            )
            startActivity(intent)
        }

//        val btn_doctor = findViewById<Button>(R.id.bottom_1)
//        btn_doctor.setOnClickListener {
//            Toast.makeText(this, "Hola, un gusto contáctanos.!", Toast.LENGTH_SHORT).show()
//        }

        //lamamos a la funcion
        verrecycler()

    }




    private fun verrecycler(){
        adapterDoc = AdapterDoctor(cargalista())
        binding.recyclerView.adapter = adapterDoc
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }


    private fun cargalista(): MutableList<ItemUsu>{
        val lista = mutableListOf<ItemUsu>()
        lista.add(ItemUsu("Nombre: Dr. Juan Poncel", "Especialidad: Odontología general",
            "Teléfono: 73231221", "Email: juanponcel@gmail.com", "Dirección: Avenida de Americas N#22"))
        lista.add(ItemUsu("Nombre: Dr. Carlos Rios", "Especialidad: Ortodoncia",
            "Teléfono: 73233443", "Email: carlosrios@gmail.com", "Dirección: Avenida del maestro N#122"))
        lista.add(ItemUsu("Nombre: Dr. Franco Diaz", "Especialidad: Cirugía oral",
            "Teléfono: 63261221", "Email: francodiaz@gmail.com", "Dirección: Calle Brazil N12"))
        lista.add(ItemUsu("Nombre: Dr. Jhon Ruiz", "Especialidad: Endodoncia",
            "Teléfono: 78230221", "Email: jhonruiz@gmail.com", "Dirección: Avenida de ejercito N#34"))
        lista.add(ItemUsu("Nombre: Dr. Jhonny Jones", "Especialidad: Periodoncia",
            "Teléfono: 75443221", "Email: jhonnyjones@gmail.com", "Dirección: Calle Argentina N#52"))

        return lista
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
