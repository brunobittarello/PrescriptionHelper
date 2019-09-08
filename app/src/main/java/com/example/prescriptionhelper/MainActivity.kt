package com.example.prescriptionhelper

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.widget.SearchView
import com.example.prescriptionhelper.internal.AppMemoryManager

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    var dataResult = mutableListOf<String>()
    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppMemoryManager.load(this)

        //http://technxt.net/how-to-create-listview-in-android/
        dataResult.addAll(AppMemoryManager.data.getPatientOptions())
        val lvResults = findViewById<ListView>(R.id.lvResults)
        adapter = ArrayAdapter(this, R.layout.search_person_item2, dataResult)
        lvResults.adapter = adapter

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
        val intent = Intent(this, PatientActivity::class.java);
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
}
