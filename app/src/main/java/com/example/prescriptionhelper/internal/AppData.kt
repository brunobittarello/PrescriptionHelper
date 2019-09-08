package com.example.prescriptionhelper.internal

import com.example.prescriptionhelper.models.Patient

class AppData {

    var patients: MutableList<Patient> = mutableListOf()
    private var patientOption = mutableListOf<String>()
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

    fun queryPatient(query: String): List<String>
    {
        if (query == "")
            return getPatientOptions()

        return getPatientOptions().filter { l -> l.contains(query, true) }
    }

    fun getPatientOptions() : MutableList<String>
    {
        if (!isPatientOptionDirty)
            return patientOption

        patientOption.clear()
        for (patient in patients)
            patientOption.add(patient.toString())
        return patientOption
    }
}