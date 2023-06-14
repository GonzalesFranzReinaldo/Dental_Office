package com.aristidevs.appsis301.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.aristidevs.appsis301.R

class InformeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informe)



        //para spinner de medicos
        //val spinnerMedico = findViewById<Spinner>(R.id.spinner_medicos)

        //variable que tendra el array de las especialidades
        //val optionsEspecialidades = arrayOf("Especialidad 1", "Especialidad 2",
         //   "Especialidad 3", "Especialidad 4", "Especialidad 5", "Especialidad 6")
       // spinnerEspecialidad.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionsEspecialidades)

        //para los pagos
        val pagados = findViewById<Spinner>(R.id.spinner_pagados)
        val optionPago = arrayOf("Verificar", " - Efectivo: Bs. 145,40")
        pagados.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionPago)

        //para los No pagados
        val no_Pagados = findViewById<Spinner>(R.id.spinner_no_Pagados)
        val optionNoPagado = arrayOf("Estado", " - Efectivo: Bs. 00,00")
        no_Pagados.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionNoPagado)

    }
}