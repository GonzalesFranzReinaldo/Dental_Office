package com.aristidevs.appsis301.ui

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
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
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.get
import com.aristidevs.appsis301.R
import com.google.android.material.snackbar.Snackbar
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
        val btnNext = findViewById<Button>(R.id.btn_siguiente)
        val btnNext2 = findViewById<Button>(R.id.btn_siguiente2)
        //para el buttom confirmar la cita
        val btnConfirm = findViewById<Button>(R.id.btn_confirmar)
        //para el bottom volver al menu
        val btn_volverMenu = findViewById<Button>(R.id.btn_volverMenu)


        //para el primer cardview siguiente
        val cvNext = findViewById<CardView>(R.id.cv_siguiente)
        //para el segundo cardview cornfirma
        val cvConfirm = findViewById<CardView>(R.id.cv_confirmar)
        //para el tercer carview resumen
        val cvResumen = findViewById<CardView>(R.id.cv_resumenCitas)

        //variables para validar los campos vacios del texto
        //para la descripcion de la cita
        val etDescription = findViewById<EditText>(R.id.et_description)
        //para el campo de la fecha
        val etScheduledDate = findViewById<EditText>(R.id.et_fecha)

        //variable para crear cita (Appointment)
        val create_Appointment = findViewById<LinearLayout>(R.id.linearlayout_create_cita)


        //para dar el bottom siguiente el cardview se oculta y muestra el segundo carview
        btnNext.setOnClickListener{
            //si campo esta vacio no debe seguir al paso siguiente
            if(etDescription.text.toString() == ""){
                etDescription.error = ""
                etDescription.error = "Debe completar el campo de descripción"
            }else{
                if(etDescription.text.toString().length < 3){
                    etDescription.error = "La descripción es demasiada corta"
                }else{
                    cvNext.visibility = View.GONE
                    cvConfirm.visibility = View.VISIBLE
                    btn_volverMenu.visibility = View.GONE
                }
            }
        }


        //al clikear el botton siguiente2 nos debe mostrar el cardview tres de resumen citas
        btnNext2.setOnClickListener{
            //si el campo de la fecha esta vacia
            if(etScheduledDate.text.toString().isEmpty()){
                etScheduledDate.error = ""
                Snackbar.make(etScheduledDate, "Debe escoger una fecha para la cita", Snackbar.LENGTH_SHORT).show()
            }else{
                //para validar la hora
                if(selectedRadioButtom == null){
                    Snackbar.make(create_Appointment, "Debe seleccionar una hora para la cita", Snackbar.LENGTH_SHORT).show()

                }else{
                    showAppointmentDataToConfirm()

                    cvConfirm.visibility = View.GONE
                    cvResumen.visibility = View.VISIBLE
                    Toast.makeText(applicationContext, "Verifique que los Datos de la Cita esten claros",  Toast.LENGTH_SHORT).show()
                }
            }
        }


        //cuando al corfirmar la cita le muestre el mensaje
        btnConfirm.setOnClickListener{
            Toast.makeText(applicationContext, "Su cita fue realizada exitosamente", Toast.LENGTH_SHORT).show()
            btn_volverMenu.visibility = View.VISIBLE
        }

        //bottom para salir de la cita al menu
        btn_volverMenu.setOnClickListener{
            val intent = Intent(this@CreateCitasActivity, MenuActivity::class.java)
            //startActivity(intent)
            finish()
        }


        //para spinner especialidad
        val spinnerEspecialidad = findViewById<Spinner>(R.id.spinner_especialidades)

        //para spinner de medicos
        val spinnerMedico = findViewById<Spinner>(R.id.spinner_medicos)

        //variable que tendra el array de las especialidades
        val optionsEspecialidades = arrayOf("Especialidad 1", "Especialidad 2",
            "Especialidad 3", "Especialidad 4", "Especialidad 5", "Especialidad 6")
        spinnerEspecialidad.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionsEspecialidades)

        //variable que tendra el array de los medicos
        val optionsDoctor = arrayOf("Dr. Juan Poncel", "Dr. Carlos Rios", "Dr. Franco Diaz", "Dr. Jhon Ruiz", "Dr. Jonny Jones")

        spinnerMedico.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionsDoctor)

    }



    // funcion para el tercer carview en el resumen --> mostrar datos de cita para confirmar
    private fun showAppointmentDataToConfirm(){

        val tvConfirmDescription = findViewById<TextView>(R.id.tv_resumenDescripcion)
        val tvConfirmSpecialty = findViewById<TextView>(R.id.tv_resumenEspecialidad)
        val tvConfirmType = findViewById<TextView>(R.id.tv_resumenTipoConsulta)
        val tvConfirmDoctorName = findViewById<TextView>(R.id.tv_resumenMedico)
        val tvConfirmDate = findViewById<TextView>(R.id.tv_resumenFecha)
        val tvConfirmTime = findViewById<TextView>(R.id.tv_resumenHora)

        //para intanciar los ides de los campos de la descripcion
        val etDescription = findViewById<EditText>(R.id.et_description)
        val spinnerSpecialties = findViewById<Spinner>(R.id.spinner_especialidades)
        val radioGroupType = findViewById<RadioGroup>(R.id.radio_group_type)
        val spinnerDoctor = findViewById<Spinner>(R.id.spinner_medicos)
        val etAgendarCita = findViewById<EditText>(R.id.et_fecha)


        //para asignar los valores a los texview del tercer carview
        tvConfirmDescription.text = etDescription.text.toString()
        tvConfirmSpecialty.text = spinnerSpecialties.selectedItem.toString()

        val selectRadioBtnId = radioGroupType.checkedRadioButtonId
        val selectedtRadioType = radioGroupType.findViewById<RadioButton>(selectRadioBtnId)
        tvConfirmType.text = selectedtRadioType.text.toString()

        tvConfirmDoctorName.text = spinnerDoctor.selectedItem.toString()
        tvConfirmDate.text = etAgendarCita.text.toString()
        tvConfirmTime.text = selectedRadioButtom?.text.toString()
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

            etScheduleDate.error = null
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


    //funcion para la alerta de salir del proceso de la cita
    override fun onBackPressed() {

        //para el primer cardview siguiente
        val cvNext = findViewById<CardView>(R.id.cv_siguiente)
        //para el segundo cardview cornfirma
        val cvConfirm = findViewById<CardView>(R.id.cv_confirmar)
        //para el tercer carview resumen
        val cvResumen = findViewById<CardView>(R.id.cv_resumenCitas)
        //para el bottom volver al menu
        val btn_volverMenu = findViewById<Button>(R.id.btn_volverMenu)


        if(cvResumen.visibility == View.VISIBLE){
            cvResumen.visibility = View.GONE
            btn_volverMenu.visibility = View.GONE
            cvConfirm.visibility = View.VISIBLE
        }else{
            if(cvConfirm.visibility == View.VISIBLE){
                cvConfirm.visibility = View.GONE
                btn_volverMenu.visibility = View.GONE
                cvNext.visibility = View.VISIBLE
            }else{
                if(cvNext.visibility == View.VISIBLE){
                    btn_volverMenu.visibility = View.GONE
                    //variable que va acontruir el proceso
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("¿Esta seguro que desea salir? :(")
                    builder.setMessage("Si abandona el registro, los datos ingresados se perderan.")

                    //builder.setPositiveButton("Salir"){ dialog, which ->}
                    builder.setPositiveButton("Salir"){_, _ ->
                        finish()
                        Toast.makeText(this, "Cita interrumpida", Toast.LENGTH_SHORT).show()
                    }
                    builder.setNegativeButton("Continuar"){ dialog, _ ->
                        dialog.dismiss()
                    }

                    //una vez se desea continuar mostrara el proceso
                    val dialog = builder.create()
                    dialog.show()
                }
            }
        }


//        btn_volverMenu.setOnClickListener{
//            val intent = Intent(this@CreateCitasActivity, MenuActivity::class.java)
//            //startActivity(intent)
//            finish()
//        }

    }



}