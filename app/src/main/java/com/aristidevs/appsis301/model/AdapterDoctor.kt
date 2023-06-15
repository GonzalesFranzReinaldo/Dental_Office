package com.aristidevs.appsis301.model

import android.app.ActivityManager.TaskDescription
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
            LayoutInflater.from(parent.context).inflate(R.layout.datos_doctor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AdapterDoctor.ViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.especiality.text = item.especiality
        holder.phone.text = item.phone
        holder.correo.text = item.email
        holder.direcction.text = item.direction
        //Glide.with(holder.itemView.context).load(item.imgn).circleCrop().into(holder.img)
        holder.bottom.setOnClickListener {
//            val activity = it.context as AppCompatActivity
//            Toast.makeText(activity,"Hola soy ${item.name}", Toast.LENGTH_LONG).show()
//            print("Hola, soy ${item.name}")
            print("Hola, un gusto contáctanos")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.nombre)
        val especiality: TextView = view.findViewById(R.id.especialidad)
        val phone: TextView = view.findViewById(R.id.telefono)
        val correo: TextView = view.findViewById(R.id.correo_electronico)
        val direcction: TextView = view.findViewById(R.id.direccion)
        val bottom: Button = view.findViewById(R.id.bottom_1)
    }
}