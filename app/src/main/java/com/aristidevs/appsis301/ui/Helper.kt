package com.aristidevs.appsis301.ui

import android.content.Context
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth

//fun Context.login(email:String){
//
//    val intent = Intent(this, MenuActivity::class.java).apply {
//        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//    }
//    startActivity(intent)
//}

fun Context.login(email:String){
    val intent = Intent(this, MenuActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    startActivity(intent)
}

fun Context.logout(){

    FirebaseAuth.getInstance().signOut()
    val intent = Intent(this, AuthActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    startActivity(intent)
}