package com.example.prescriptionhelper.internal

import com.example.prescriptionhelper.models.Patient

class AppData {

    var patients: MutableList<Patient> = mutableListOf()
    private var patientOptions = mutableListOf<Patient>()
    var isPatientOptionDirty : Boolean = true

    fun addPatient(patient: Patient)
    {
        patients.add(patient)
        AppMemoryManager.save()
        isPatientOptionDirty = true
    }

    fun deleteSelectedPatient()
    {
        patients.remove(AppMemoryManager.patientSelected)
        AppMemoryManager.save()
        isPatientOptionDirty = true
    }

    fun queryPatient(query: String): List<Patient>
    {
        if (query == "")
            return getPatientOptions()

        return getPatientOptions().filter { p -> p.name.contains(query, true) }
    }

    fun getPatientOptions() : MutableList<Patient>
    {
        if (!isPatientOptionDirty)
            return patientOptions

        patientOptions.clear()
        for (patient in patients)
            patientOptions.add(patient)
        return patientOptions
    }
}