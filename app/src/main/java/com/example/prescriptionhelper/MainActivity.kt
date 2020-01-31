package com.example.prescriptionhelper

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.widget.SearchView
import com.example.prescriptionhelper.internal.AppMemoryManager
import com.example.prescriptionhelper.internal.PatientAdapter
import com.example.prescriptionhelper.models.Patient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener, AdapterView.OnItemClickListener {

    var dataResult = mutableListOf<Patient>()
    lateinit var adapter: PatientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppMemoryManager.load(this)

        //http://technxt.net/how-to-create-listview-in-android/
        dataResult.addAll(AppMemoryManager.data.getPatientOptions())
        val lvResults = findViewById<ListView>(R.id.lvResults)
        adapter = PatientAdapter(this, dataResult)
        lvResults.adapter = adapter
        lvResults.onItemClickListener = this

        btn_patient_add.setOnClickListener { view -> onButtonAddPatientClick(view) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_item, menu)

        val menuItem = menu?.findItem(R.id.action_search)
        val searchView = menuItem?.actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(this)
        return true
    }

    //Buttons
    fun onButtonAddPatientClick(view: View) {
        val intent = Intent(this, PatientActivity::class.java)
        startActivity(intent)
    }

    //OnQueryTextListener
    override fun onQueryTextSubmit(query: String ) : Boolean {
        Log.e("Search Sub", query)
        return true
    }

    override fun onQueryTextChange(query: String ) : Boolean {
        Log.e("Search", query)
        dataResult.clear()
        dataResult.addAll(AppMemoryManager.data.queryPatient(query))
        adapter.notifyDataSetChanged()
        return true
    }

    //OnItemClickListener
    override fun onItemClick(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        AppMemoryManager.patientSelected = dataResult[pos]
        Log.d("SELECIONADO", AppMemoryManager.patientSelected?.name)
        val intent = Intent(this, PatientActivity::class.java)
        startActivity(intent)
    }
}
