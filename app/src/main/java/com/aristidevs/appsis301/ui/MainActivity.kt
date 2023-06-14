package com.aristidevs.appsis301.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

//import android.widget.TextView
import android.content.Intent
import android.util.Log
import com.aristidevs.appsis301.R
import com.google.firebase.analytics.FirebaseAnalytics


//import android.widget.Button
//import com.aristidevs.appsis301.util.PreferenceHelper
//import com.aristidevs.appsis301.util.PreferenceHelper.get
//import com.aristidevs.appsis301.util.PreferenceHelper.set

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //buttom para iniciar y que vaya a login
        val btn_Iniciar = findViewById<Button>(R.id.buttonIniciar) as Button

        btn_Iniciar.setOnClickListener(){
            val iniciar = Intent(this, AuthActivity::class.java)
            startActivity(iniciar)
        }

        val analitycs = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message","comenzando app")

        analitycs.logEvent("MainActivity",bundle)

    }

}