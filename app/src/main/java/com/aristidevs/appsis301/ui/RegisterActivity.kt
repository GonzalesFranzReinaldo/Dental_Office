package com.aristidevs.appsis301.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.aristidevs.appsis301.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        /*
        //funcionalidad para el boton volver a logi
        var tvGoLogin = findViewById<TextView>(R.id.tv_go_to_login)
        tvGoLogin.setOnClickListener{
            GoToLogin()
        }

         */
    }

    //funcion que vaya a main activity login
//    private fun GoToLogin(){
//        val i = Intent(this, LoginActivity::class.java)
//        startActivity(i)
//    }
}