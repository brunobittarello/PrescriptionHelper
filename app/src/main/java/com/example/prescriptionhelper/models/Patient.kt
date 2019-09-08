package com.example.prescriptionhelper.models

class Patient {
    var name: String = ""
    var prescriptions = mutableListOf<Prescription>()

    constructor(name: String)
    {
        this.name = name
    }

    override fun toString() : String
    {
        return name
    }
}