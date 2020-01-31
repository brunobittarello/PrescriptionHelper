package com.example.prescriptionhelper.internal

import android.util.Log
import com.beust.klaxon.Klaxon
import android.content.SharedPreferences
import android.content.Context
import com.example.prescriptionhelper.models.Patient
import com.example.prescriptionhelper.models.Prescription


//About Singletons
//https://alexdunn.org/2018/01/30/android-kotlin-basics-static-classes/
//https://medium.com/@BladeCoder/kotlin-singletons-with-argument-194ef06edd9e

//About Storage
//https://developer.android.com/guide/topics/data/data-storage
object AppMemoryManager {

    val PREFS_PROFILE = "MyProfile"
    val PREFS_DATA = "MyData"

    private lateinit var preferences: SharedPreferences

    var patientSelected : Patient? = null
    lateinit var data : AppData


    fun save()
    {
        Log.w("AppMemoryManager", "SAVE")
        val edit = preferences.edit()
        val jsonAlarm = Klaxon().toJsonString(data)
        edit.putString(PREFS_DATA, jsonAlarm)
        edit.apply()
        Log.w("AppMemoryManager", "SAVED")
    }

    fun load(context: Context)
    {
        Log.w("AppMemoryManager", "LOAD")
        preferences = context.getSharedPreferences(PREFS_PROFILE, 0)
        val jsonData = preferences.getString(PREFS_DATA, "")


        if (jsonData == null || jsonData == "")
            data = loadDebugData()
        else
            data = Klaxon().parse<AppData>(jsonData) ?: loadDebugData()

        Log.w("AppMemoryManager", "LOADED")
    }


    private fun loadDebugData() : AppData
    {
        Log.w("AppMemoryManager", "loadDebugData")

        val data = AppData()

        var patient = Patient("Bruno")
        var prescription = Prescription()
        prescription.text = "Tem nada"
        patient.prescriptions.add(prescription)

        prescription = Prescription()
        prescription.text = "Teve algo"
        patient.prescriptions.add(prescription)

        data.patients.add(patient)

        patient = Patient("Matheus")
        prescription = Prescription()
        prescription.text = "É doente"
        patient.prescriptions.add(prescription)

        prescription = Prescription()
        prescription.text = "Gordo"
        patient.prescriptions.add(prescription)

        data.patients.add(patient)

        data.patients
        return data
    }
}