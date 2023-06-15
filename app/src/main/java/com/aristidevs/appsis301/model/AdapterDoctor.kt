package com.aristidevs.appsis301.model

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.aristidevs.appsis301.R
import com.aristidevs.appsis301.io.response.ItemUsu
import com.bumptech.glide.Glide

class AdapterDoctor (private var items: List<ItemUsu>):
RecyclerView.Adapter<AdapterDoctor.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterDoctor.ViewHolder {
        return AdapterDoctor.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.datos_medico, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AdapterDoctor.ViewHolder, position: Int) {
        val item = items[position]
        holder.nombre.text = item.nombre
        holder.especialidad.text = item.especialidad
        holder.telefono.text = item.telefono
        holder.correo.text = item.correo_electronico
        holder.direccion.text = item.direccion
        Glide.with(holder.itemView.context).load(item.imagen).circleCrop().into(holder.perfil_doctor)
        holder.bottom.setOnClickListener{
            val activity = it.context as AppCompatActivity
            Toast.makeText(activity, "Hola, soy ${item.nombre}", Toast.LENGTH_SHORT).show()
            print("Hola, soy ${item.nombre}")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nombre: TextView = view.findViewById(R.id.nombre)
        val especialidad: TextView = view.findViewById(R.id.especialidad)
        val telefono: TextView = view.findViewById(R.id.telefono)
        val correo: TextView = view.findViewById(R.id.correo_electronico)
        val direccion: TextView = view.findViewById(R.id.direccion)
        val perfil_doctor: ImageView = view.findViewById(R.id.lafoto)
        val bottom: Button = view.findViewById(R.id.bottom_1)
    }
}