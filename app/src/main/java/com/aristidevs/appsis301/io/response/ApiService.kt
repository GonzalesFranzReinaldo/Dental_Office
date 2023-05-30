package com.aristidevs.appsis301.io.response

import com.aristidevs.appsis301.io.response.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    //para el login
    @POST(value = "login")
    fun postLogin(@Query(value = "email") email: String, @Query(value = "password") password: String):
            Call<LoginResponse>


    //para logout cerrar la sesion
    @POST(value = "logout")
    fun porLogout(@Header(value = "Authorization") authHeader: String):
            Call<Void>



    companion object Factory{
        private const val BASE_URL = "http://192.168.136.79:8000/api/auth/"
        fun  create(): ApiService{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }

}

