package com.aristidevs.appsis301.ui

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.get
import com.aristidevs.appsis301.R
import java.util.Calendar

class CreateCitasActivity : AppCompatActivity() {

    //para instanciar fecha en el calendario
    private val selectedCalendar = Calendar.getInstance()

    //para guardar el ultimo referencia de los radiobuttoms para que no se repita
    private  var selectedRadioButtom: RadioButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_citas)

        //para el buttom siguiente
        val btnNex = findViewById<Button>(R.id.btn_siguiente)

        //para el buttom confirmar la cita
        val btnConfirm = findViewById<Button>(R.id.btn_confirmar)

        //para el primer cardview siguiente
        val cvNext = findViewById<CardView>(R.id.cv_siguiente)

        //para el segundo cardview cornfirma
        val cvConfirm = findViewById<CardView>(R.id.cv_confirmar)


        //para dar el bottom siguiente el cardview se oculta y muestra el segundo carview
        btnNex.setOnClickListener{
            cvNext.visibility = View.GONE
            cvConfirm.visibility = View.VISIBLE
        }

        //cuando al corfirmar la cita le muestre el mensaje
        btnConfirm.setOnClickListener{
            Toast.makeText(applicationContext, "Su cita fue realizada exitosamente", Toast.LENGTH_SHORT).show()
        }


        //para spinner especialidad
        val spinnerEspecialidad = findViewById<Spinner>(R.id.spinner_especialidades)

        //para spinner de medicos
        val spinnerMedico = findViewById<Spinner>(R.id.spinner_medicos)

        //variable que tendra el array de las especialidades
        val optionsEspecialidades = arrayOf("Especialidad 1", "Especialidad 2",
            "Especialidad 3", "Especialidad 4", "Especialidad 5")
        spinnerEspecialidad.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionsEspecialidades)

        //variable que tendra el array de los medicos
        val optionsDoctor = arrayOf("Medico 1", "Medico 2", "Medico 3", "Medico 4", "Medico 5")

        spinnerMedico.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionsDoctor)


        //val position = optionsDoctor[1]


       // Toast.makeText(applicationContext, "aqui esta xd $position", Toast.LENGTH_SHORT).show()
    }

    //metodo para onclick para programar la cita en el calendario
    fun onClickScheduledDate(v: View?){
        val etScheduleDate = findViewById<EditText>(R.id.et_fecha)

        //val selectedCalendar = Calendar.getInstance()
        val year = selectedCalendar.get(Calendar.YEAR)
        val month = selectedCalendar.get(Calendar.MONTH)
        val dayOfMonth = selectedCalendar.get(Calendar.DAY_OF_MONTH)
        //val listener = DatePickerDialog.OnDateSetListener{datePicker, y, m, d ->
        //   selectedCalendar.set(y, m, d)
        // etScheduleDate.setText("$y-$m-$d")}
        val listener = DatePickerDialog.OnDateSetListener{view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
            selectedCalendar.set(year, month, dayOfMonth)
            etScheduleDate.setText(resources.getString(R.string.date_format,
                year,
                (month+1).twoDigits(),
                dayOfMonth.twoDigits()))

            displayRadioButtoms()
        }

        //para el rango de los dias del mes
        val datePickerDialog = DatePickerDialog(this, listener, year, month, dayOfMonth)
        val datePicker = datePickerDialog.datePicker
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        datePicker.minDate =  calendar.timeInMillis

        datePickerDialog.show()
    }


    private fun Int.twoDigits()
         = if(this >= 10) this.toString() else "0$this"


    //funcion para mostrar los radio buttoms de horas
    private fun displayRadioButtoms(){
        /*val radioGroup = findViewById<RadioGroup>(R.id.radio_group)
        radioGroup.clearCheck()
        radioGroup.removeAllViews()*/

        val radioGroupLeft = findViewById<LinearLayout>(R.id.radio_group_izq)
        val radioGroupRigth = findViewById<LinearLayout>(R.id.radio_group_der)

        //para que no se repitan los radio buttoms
        radioGroupLeft.removeAllViews()
        radioGroupRigth.removeAllViews()

        selectedRadioButtom = null

        var goToLeft = true

        //variable que va a almacenar los radioButtoms de las horas
        val hours = arrayOf("08:00 AM", "08:30 AM", "09:00 AM", "09:30 AM", "10:00 AM",
                           "11:00 AM", "12:00 AM", "14:00 PM","16:00 PM", "18:00 PM")
        hours.forEach {
            val radioButton = RadioButton(this)
            radioButton.id = View.generateViewId()
            radioButton.text = it

            radioButton.setOnClickListener{ view ->
                selectedRadioButtom?.isChecked = false
                selectedRadioButtom = view as RadioButton?
                selectedRadioButtom?.isChecked = true
            }

            if(goToLeft){
                radioGroupLeft.addView(radioButton)
            }else{
                radioGroupRigth.addView(radioButton)
            }
            goToLeft = !goToLeft
        }
    }


}