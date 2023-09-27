package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    var tvselecteddate : TextView ?= null
    var tvresult: TextView ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvresult = findViewById(R.id.display)
        tvselecteddate = findViewById(R.id.selecting)
        val datepicker: Button = findViewById(R.id.bt1)
        datepicker.setOnClickListener {
            clickdatepicker()
        }
    }


    fun clickdatepicker() {

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        var dpg = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayofMonth ->
                Toast.makeText(
                    this,
                    "year was $year , month was ${month + 1} and day was $dayofMonth ",
                    Toast.LENGTH_SHORT
                ).show()

                val selected_date = "$dayofMonth/${month + 1}/$year"
                tvselecteddate?.text = selected_date


                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selected_date)
                val selectedDateInMinutes = theDate.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate.time / 60000
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                tvresult?.text = differenceInMinutes.toString()

            },
            year,
            month,
            day
        )
        dpg.show()

    }
}

