package com.aristidevs.appsis301.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.aristidevs.appsis301.R

class bookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        val arrayAdapter:ArrayAdapter<*>

        val terminos = mutableListOf(
            "CARIES",
            "La caries es una enfermedad dental causada por la desmineralización de los tejidos duros del diente debido a la acción de los ácidos producidos por las bacterias",
            "PERIODONCIA",
            "La periodoncia es la especialidad de la odontología que se encarga del diagnóstico, prevención y tratamiento de las enfermedades que afectan a los tejidos de soporte de los dientes, como las encías, el hueso alveolar y el ligamento periodontal",
            "BLANQUEAMIENTO",
            "El blanqueamiento dental es un procedimiento estético que se utiliza para aclarar el color de los dientes y eliminar manchas y decoloraciones",
            "IMPLANTES DENTALES",
            "Los implantes dentales son estructuras de titanio que se colocan en el hueso maxilar o mandibular para reemplazar las raíces de los dientes ausentes y soportar las prótesis dentales",
            "ORTODONCIA",
            "La ortodoncia es la especialidad de la odontología que se encarga de prevenir, diagnosticar y corregir las irregularidades en la posición de los dientes y las deformidades de los huesos maxilares",
            "ENDODONCIA",
            "La endodoncia es un tratamiento dental que consiste en la eliminación de la pulpa dental infectada o inflamada, la limpieza y desinfección de los conductos radiculares, y el posterior sellado para preservar el diente",
            "PRÓTESIS",
            "Las prótesis dentales son dispositivos artificiales utilizados para reemplazar los dientes naturales ausentes y restaurar la función y apariencia de la boca.",
            "ODONTOPEDRIATRÍA",
            "La odontopediatría es la rama de la odontología que se dedica a la atención dental de los niños, desde la infancia hasta la adolescencia."
        )

        val lvdatos = findViewById<ListView>(R.id.lvDatos)

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, terminos)
        lvdatos.adapter = arrayAdapter
    }
}