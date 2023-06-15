package com.aristidevs.appsis301.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aristidevs.appsis301.R
import com.aristidevs.appsis301.databinding.ActivityAuthBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.GoogleAuthProvider

class AuthActivity : AppCompatActivity() {
    //comenzar a declarar
    private lateinit var mAuth : FirebaseAuth
    private val Google_SIGN_IN = 100
    private lateinit var binding: ActivityAuthBinding


    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: AuthStateListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //firebaseAuth = Firebase.auth

       // inicializar
        mAuth = FirebaseAuth.getInstance()

        //botonm
        buttom()
        //onActivityResult()

//        binding.loginGoogle.setOnClickListener{
//            singIn(binding.DatoEmail.text.toString(), binding.DatoPassword.text.toString())
//        }

    }

//    private fun singIn(email:String,password: String){
//
//        if(binding.DatoEmail.text.toString().isBlank() or binding.DatoPassword.text.toString().isBlank()){
//
//            Toast.makeText(baseContext,"Por favor ingrese los datos", Toast.LENGTH_LONG).show()
//        }
//        else{
//            firebaseAuth.signInWithEmailAndPassword(email,password)
//                .addOnCompleteListener(this) {task ->
//                    if(task.isSuccessful){
////                        val user = firebaseAuth.currentUser
////                        Toast.makeText(baseContext,user?.uid.toString(), Toast.LENGTH_LONG).show()
//                        val i = Intent(this, MenuActivity::class.java)
//                        startActivity(i)
//
//                    }
//                    else{
//                        Toast.makeText(baseContext,"Correo o contraseña incorrecta", Toast.LENGTH_LONG).show()
//                    }
//                }
//        }
//
//    }

    private fun buttom(){
        binding.loginGoogle.setOnClickListener{
            //configurar google sign in
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googleClient = GoogleSignIn.getClient(this, googleConf)
            val signInIntent = googleClient.signInIntent
            startActivityForResult(signInIntent, Google_SIGN_IN)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == Google_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                //google sign in was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                if(account != null){
                    Log.d("Tag", "firebasegoogleid $account.id")
                    firebaseAuthWithGoogle(account.idToken!!)
                }else{
                    Toast.makeText(this, "Correo no existe", Toast.LENGTH_LONG).show()
                }
            }catch (e:ApiException){
                Log.w("Tag", "google sign in failed $e")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    //Sign in success, update UI with the signed-in user's informatio
                    Log.d("Tag", "signInWithCredential:success")
                    val user = mAuth.currentUser?.email.toString()
                    login(user)
                }else{
                    //if sign in fails, display a message to the user.
                    Log.w("Tag", "signInWithCredential:failure", task.exception)
                    Toast.makeText(this, "No se pudo loguear", Toast.LENGTH_LONG).show()
                }
            }
    }
}


