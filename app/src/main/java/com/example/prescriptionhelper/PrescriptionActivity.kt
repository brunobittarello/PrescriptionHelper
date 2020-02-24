package com.example.prescriptionhelper

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class PrescriptionActivity : AppCompatActivity() {

    lateinit var startDate: Calendar
    lateinit var endDate: Calendar
    private val DATE_FORMAT: String = "dd/MM/yyyy"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prescription)

        val tvMedStartDate: TextView  = findViewById(R.id.tv_med_startDate)
        val tvMedEndDate: TextView  = findViewById(R.id.tv_med_endDate)
        tvMedStartDate.text = SimpleDateFormat(DATE_FORMAT).format(System.currentTimeMillis())
        tvMedEndDate.text = SimpleDateFormat(DATE_FORMAT).format(System.currentTimeMillis())

        var startDate = Calendar.getInstance()
        var endDate = Calendar.getInstance()

        tvMedStartDate.setOnClickListener {
            DatePickerDialog(this, createDateListener(tvMedStartDate, startDate),
                startDate.get(Calendar.YEAR),
                startDate.get(Calendar.MONTH),
                startDate.get(Calendar.DAY_OF_MONTH)).show()
        }

        tvMedEndDate.setOnClickListener {
            DatePickerDialog(this, createDateListener(tvMedEndDate, endDate),
                endDate.get(Calendar.YEAR),
                endDate.get(Calendar.MONTH),
                endDate.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun createDateListener(textView: TextView, cal: Calendar): DatePickerDialog.OnDateSetListener {
        return DatePickerDialog.OnDateSetListener { view, year, month, day ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, day)

            val sdf = SimpleDateFormat(DATE_FORMAT, Locale.US)
            textView.text = sdf.format(cal.time)
        }
    }
}
