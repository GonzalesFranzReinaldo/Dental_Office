package com.aristidevs.appsis301.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aristidevs.appsis301.R
import com.aristidevs.appsis301.model.Queries

class ConsultasAdapter(private val queries: ArrayList<Queries>)
    : RecyclerView.Adapter<ConsultasAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        //creamos variable para los ides
        val tvQueriesId = itemView.findViewById<TextView>(R.id.tv_id)
        val tvDoctorName = itemView.findViewById<TextView>(R.id.tv_medico)
        val tvScheduledDate = itemView.findViewById<TextView>(R.id.tv_fecha)
        val tvScheduledTime = itemView.findViewById<TextView>(R.id.tv_hora)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_consultas, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val querie = queries[position]

        holder.tvQueriesId.text = "Consulta #${querie.id}"
        holder.tvDoctorName.text = querie.doctorName
        holder.tvScheduledDate.text = "Atención el dia ${querie.scheduledDate}"
        holder.tvScheduledTime.text = "Hora de atención a las ${querie.scheduledTime}"
    }

    override fun getItemCount() = queries.size

}