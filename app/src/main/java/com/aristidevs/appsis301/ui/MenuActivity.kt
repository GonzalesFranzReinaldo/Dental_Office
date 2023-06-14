package com.aristidevs.appsis301.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.aristidevs.appsis301.R
import com.aristidevs.appsis301.databinding.ActivityAuthBinding
import com.aristidevs.appsis301.databinding.ActivityMenuBinding
import com.aristidevs.appsis301.io.response.ApiService
import com.aristidevs.appsis301.util.PreferenceHelper
import com.aristidevs.appsis301.util.PreferenceHelper.set
import com.aristidevs.appsis301.util.PreferenceHelper.get
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MenuActivity : AppCompatActivity() {


    private lateinit var mAuth : FirebaseAuth
    private val Google_SIGN_IN = 100
    //private lateinit var binding: ActivityAuthBinding

    private lateinit var binding: ActivityMenuBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener


    //para los bottones flotantes
    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim) }

    private var clicked = false


    //incluir apiService
//    private val apiService by lazy {
//        ApiService.create()
//    }
//
//    private val preferences by lazy {
//        PreferenceHelper.defaultPrefs(this)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //para login al presionar el cerrar la sesion vuelva a login
//        val btnLogout = findViewById<Button>(R.id.btn_logout)
//        btnLogout.setOnClickListener{
//            performLogout()
//        }

        //para el bottom reservar citas
        val btnReservar_cita = findViewById<Button>(R.id.btn_reservar_cita)
        btnReservar_cita.setOnClickListener{
            goToCreateCitas()
        }

        //para el bottom mis consultas  para ir al activity consultas
        val btnMisConsultas = findViewById<Button>(R.id.btn_mis_consultas) as Button

        btnMisConsultas.setOnClickListener(){
            val consultas = Intent(this@MenuActivity, ConsultasActivity::class.java)
            startActivity(consultas)
        }

        //para el buttom logout
        binding.btnLogout.setOnClickListener{logout()}

        //buttom para ir al tablero
        val btn_tablero = findViewById<ImageView>(R.id.iv_tablero)

        btn_tablero.setOnClickListener{
            val tablero = Intent(this@MenuActivity, TableroActivity::class.java)
            startActivity(tablero)
        }




        //para los botones flotantes
        val btn_options = findViewById<FloatingActionButton>(R.id.btn_options)
        val btn_perfilClinica = findViewById<FloatingActionButton>(R.id.btn_perfilClinica)
        val btn_user = findViewById<FloatingActionButton>(R.id.btn_user)
        val btn_book = findViewById<FloatingActionButton>(R.id.btn_book)
//
//
        btn_options.setOnClickListener{
            onAddButtomClicked()
        }

        btn_perfilClinica.setOnClickListener{
            Toast.makeText(this, "Botón edit pulsado", Toast.LENGTH_SHORT).show()
        }
        btn_user.setOnClickListener{
            val intent = Intent(this@MenuActivity, PerfilActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Perfil del usuario", Toast.LENGTH_SHORT).show()
        }


        //para los informes
        val informes = findViewById<ImageView>(R.id.iv_informes)

        informes.setOnClickListener{
            val intent = Intent(this@MenuActivity, InformeActivity::class.java)
            startActivity(intent)
        }

    }

    private fun onAddButtomClicked(){
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked

    }

    private fun setVisibility(clicked: Boolean){
        val btn_perfilClinica = findViewById<FloatingActionButton>(R.id.btn_perfilClinica)
        val btn_user = findViewById<FloatingActionButton>(R.id.btn_user)
        val btn_book = findViewById<FloatingActionButton>(R.id.btn_book)

        //para los textos
        val tv_perfilClinica = findViewById<TextView>(R.id.tv_dental)
        val tv_user = findViewById<TextView>(R.id.tv_user)
        val tv_book = findViewById<TextView>(R.id.tv_book)

        if(!clicked){
            btn_perfilClinica.visibility = View.VISIBLE
            btn_user.visibility = View.VISIBLE
            btn_book.visibility = View.VISIBLE

            tv_perfilClinica.visibility = View.VISIBLE
            tv_user.visibility = View.VISIBLE
            tv_book.visibility = View.VISIBLE
        }else{
            btn_perfilClinica.visibility = View.INVISIBLE
            btn_user.visibility = View.INVISIBLE
            btn_book.visibility = View.INVISIBLE

            tv_perfilClinica.visibility = View.INVISIBLE
            tv_user.visibility = View.INVISIBLE
            tv_book.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean){
        val btn_options = findViewById<FloatingActionButton>(R.id.btn_options)
        val btn_perfilClinica = findViewById<FloatingActionButton>(R.id.btn_perfilClinica)
        val btn_user = findViewById<FloatingActionButton>(R.id.btn_user)
        val btn_book = findViewById<FloatingActionButton>(R.id.btn_book)

        //para los textos
        val tv_perfilClinica = findViewById<TextView>(R.id.tv_dental)
        val tv_user = findViewById<TextView>(R.id.tv_user)
        val tv_book = findViewById<TextView>(R.id.tv_book)

        if(!clicked){
            btn_perfilClinica.startAnimation(fromBottom)
            btn_user.startAnimation(fromBottom)
            btn_book.startAnimation(fromBottom)

            tv_perfilClinica.startAnimation(fromBottom)
            tv_user.startAnimation(fromBottom)
            tv_book.startAnimation(fromBottom)

            btn_options.startAnimation(rotateOpen)
        }else{
            btn_perfilClinica.startAnimation(toBottom)
            btn_user.startAnimation(toBottom)
            btn_book.startAnimation(toBottom)

            tv_perfilClinica.startAnimation(toBottom)
            tv_user.startAnimation(toBottom)
            tv_book.startAnimation(toBottom)

            btn_options.startAnimation(rotateClose)
        }

    }

    private fun setClickable(clicked: Boolean){
        val btn_perfilClinica = findViewById<FloatingActionButton>(R.id.btn_perfilClinica)
        val btn_user = findViewById<FloatingActionButton>(R.id.btn_user)
        val btn_book = findViewById<FloatingActionButton>(R.id.btn_book)
        if(!clicked){
            btn_perfilClinica.isClickable = true
            btn_user.isClickable = true
            btn_book.isClickable = true
        }else{
            btn_perfilClinica.isClickable = false
            btn_user.isClickable = false
            btn_book.isClickable = false
        }
    }





    //funcion para reservar cita
    private  fun goToCreateCitas(){
        val citas = Intent(this, CreateCitasActivity::class.java)
        startActivity(citas)
    }

    //metodo para volver a login
//    private fun goToLogin(){
//        val i = Intent(this, LoginActivity::class.java)
//        startActivity(i)
//        finish()
//    }

    //tambien para  cerrar la sesion
//    private fun performLogout(){
//        val jwt = preferences["jwt", ""]
//        val call = apiService.porLogout("Bearer $jwt")
//
//        call.enqueue(object: retrofit2.Callback<Void>{
//            override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                //en caso de tener una respuesta exitosa
//                clearSessionPreference()
//                goToLogin()
//            }
//
//            override fun onFailure(call: Call<Void>, t: Throwable) {
//                Toast.makeText(applicationContext, "Se produjo un error en el servidor", Toast.LENGTH_SHORT).show()
//            }
//
//        })
//    }

    //metodo para limpiar la sesion y cerrar la sesion
//    private fun clearSessionPreference(){
//        //val preferences = PreferenceHelper.defaultPrefs(this)
//        preferences["jwt"] = ""
//    }


}