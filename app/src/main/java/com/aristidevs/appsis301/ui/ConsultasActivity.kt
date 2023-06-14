package com.aristidevs.appsis301.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aristidevs.appsis301.R
import com.aristidevs.appsis301.model.Queries

class ConsultasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultas)

        //para el carview de consultas
        val rvQueries = findViewById<RecyclerView>(R.id.rv_Consultas)
        //rvQueries.layoutManager = LinearLayoutManager(this)


        val queries = ArrayList<Queries>()

        queries.add(
            Queries(1, "Doc. Juan Poncel", "15/06/2023", "09:00 AM")
        )

        queries.add(
            Queries(2, "Doc. Carlos Rios", "18/07/2023", "09:30 AM")
        )

        queries.add(
            Queries(3, "Doc. Franco Diaz", "20/07/2023", "10:00 AM")
        )

        queries.add(
            Queries(4, "Doc. Jhon Riuiz", "25/06/2023", "11:00 AM")
        )

        queries.add(
            Queries(5, "Doc. Jhonny Jones", "28/07/2023", "12:00 AM")
        )

        rvQueries.layoutManager = LinearLayoutManager( this)
        rvQueries.adapter = ConsultasAdapter(queries)
    }
}