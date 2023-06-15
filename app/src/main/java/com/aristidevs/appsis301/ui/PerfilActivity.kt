package com.aristidevs.appsis301.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.aristidevs.appsis301.R
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class PerfilActivity : AppCompatActivity() {

    lateinit var gso: GoogleSignInOptions
    lateinit var gsc: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        gsc = GoogleSignIn.getClient(this@PerfilActivity, gso)

        val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this@PerfilActivity)
        if (account != null) {
            val name = account.displayName
            val email = account.email
            val textNombre: TextView = findViewById(R.id.textname)
            val textCorreo: TextView = findViewById(R.id.textcorreo)
            textNombre.text = name
            textCorreo.text = email

        }

    }





}