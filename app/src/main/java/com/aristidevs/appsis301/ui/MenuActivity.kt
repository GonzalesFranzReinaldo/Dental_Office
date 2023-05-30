package com.aristidevs.appsis301.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.aristidevs.appsis301.R
import com.aristidevs.appsis301.io.response.ApiService
import com.aristidevs.appsis301.util.PreferenceHelper
import com.aristidevs.appsis301.util.PreferenceHelper.set
import com.aristidevs.appsis301.util.PreferenceHelper.get
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MenuActivity : AppCompatActivity() {

    //incluir apiService
    private val apiService by lazy {
        ApiService.create()
    }

    private val preferences by lazy {
        PreferenceHelper.defaultPrefs(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        //para login al presionar el cerrar la sesion vuelva a login
        val btnLogout = findViewById<Button>(R.id.btn_logout)
        btnLogout.setOnClickListener{
            performLogout()
        }

        //para el bottom reservar citas
        val btnReservar_cita = findViewById<Button>(R.id.btn_reservar_cita)
        btnReservar_cita.setOnClickListener{
            goToCreateCitas()
        }
    }

    //funcion para reservar cita
    private  fun goToCreateCitas(){
        val citas = Intent(this, CreateCitasActivity::class.java)
        startActivity(citas)
    }

    //metodo para volver a login
    private fun goToLogin(){
        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
        finish()
    }

    //tambien para  cerrar la sesion
    private fun performLogout(){
        val jwt = preferences["jwt", ""]
        val call = apiService.porLogout("Bearer $jwt")

        call.enqueue(object: retrofit2.Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                //en caso de tener una respuesta exitosa
                clearSessionPreference()
                goToLogin()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(applicationContext, "Se produjo un error en el servidor", Toast.LENGTH_SHORT).show()
            }

        })
    }


    //metodo para limpiar la sesion y cerrar la sesion
    private fun clearSessionPreference(){
        //val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["jwt"] = ""
    }
}