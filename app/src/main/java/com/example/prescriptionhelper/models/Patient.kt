package com.example.prescriptionhelper.models

import java.util.*

class Patient {
    var name: String = ""
    lateinit var createdAt: Date
    lateinit var modifiedAt: Date
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