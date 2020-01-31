package com.example.prescriptionhelper

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.prescriptionhelper.internal.AppMemoryManager
import kotlinx.android.synthetic.main.activity_patient.*


//https://developer.android.com/training/appbar?hl=pt-br
class PatientActivity : AppCompatActivity() {
    var isAdding : Boolean = false
    lateinit var edName : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadLayout()
        isAdding = AppMemoryManager.patientSelected == null
        if (!isAdding)
            loadPatient()
        AppMemoryManager.patientSelected = null
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (isAdding) return true
        menuInflater.inflate(R.menu.patient_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.home -> {
            onBackPressed()
            true
        }
        R.id.patient_menu_add -> {

            Log.d("Tanto FAz", "Tanto FAz")
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    private fun loadLayout() {
        edName = findViewById(R.id.ed_patient_name)
    }

    private fun loadPatient() {
        edName.setText(AppMemoryManager.patientSelected?.name)
    }
}
