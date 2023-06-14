package com.aristidevs.appsis301.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.aristidevs.appsis301.R
import com.aristidevs.appsis301.io.response.ApiService
import com.aristidevs.appsis301.io.response.LoginResponse
import com.aristidevs.appsis301.util.PreferenceHelper
import com.aristidevs.appsis301.util.PreferenceHelper.get
import com.aristidevs.appsis301.util.PreferenceHelper.set
import retrofit2.Call

class LoginActivity : AppCompatActivity() {

    //crear la api
    private  val apiService: ApiService by lazy {
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        //para preferencia del inicio
        val preferences = PreferenceHelper.defaultPrefs(this)
        if(preferences["jwt", ""].contains("."))
            goToMenu()
        //"success", false


        //funcionalidad del boton no tienes cuenta?
        val tvGoRegister = findViewById<TextView>(R.id.tv_go_to_register)
        tvGoRegister.setOnClickListener{
            goToRegister()
        }

        //funcionalidad del btn menu
        val btnGoMenu = findViewById<Button>(R.id.btn_go_to_menu)
        btnGoMenu.setOnClickListener{
            performLogin()
        }

    }


    //funciones metodos para llamadas
    private fun goToRegister(){
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
    }

    // funcion para el menu de consultas
    private  fun goToMenu(){
        val i = Intent(this, MenuActivity::class.java)
        startActivity(i)
        finish()
    }

    //metodo para sesion preferences una ves que inicie la sesion
    //le muestre el menu
    private fun createSessionPreference(jwt: String){
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["jwt"] = jwt
        //Toast.makeText(applicationContext, ""+jwt, Toast.LENGTH_SHORT).show()
    }


    //metodo para la validacion de la api
    private  fun performLogin(){
        val etEmail = findViewById<EditText>(R.id.et_email).text.toString()
        val etPassword = findViewById<EditText>(R.id.et_password).text.toString()

        //para mostrar el mensaje debe ingresa su correo electronico
        if(etEmail.trim().isEmpty() || etPassword.trim().isEmpty()){
            Toast.makeText(applicationContext, "Debe ingresar su correo y contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        val call = apiService.postLogin(etEmail, etPassword)
        call.enqueue(object: retrofit2.Callback<LoginResponse>{

            override fun onResponse(
                call: Call<LoginResponse>,
                response: retrofit2.Response<LoginResponse>
            ) {
                if(response.isSuccessful){
                    val loginResponse = response.body()
                    if(loginResponse == null){
                        Toast.makeText(applicationContext, "Se produjo un error en el servidor", Toast.LENGTH_SHORT).show()
                        return
                    }
                    if(loginResponse.success){
                        createSessionPreference(loginResponse.jwt)
                        goToMenu()
                    }else{
                        Toast.makeText(applicationContext, "La contraseña es incorrecta", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(applicationContext, "Se produjo un error en el servidor", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Se produjo un error en el servidor", Toast.LENGTH_SHORT).show()
            }

        })
    }


}


