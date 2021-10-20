package com.example.bonusassignments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.lang.Exception
import java.net.URL

class RecyclerViewfromAPI : AppCompatActivity() {

    private lateinit var names: MutableList<String>
    private lateinit var adapter: NamesRecyclerViewAdapter
    private lateinit var rvNames: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_viewfrom_a_p_i)

        names = mutableListOf()
        rvNames = findViewById(R.id.rvNames)
        adapter = NamesRecyclerViewAdapter(names)
        rvNames.adapter = adapter
        rvNames.layoutManager = LinearLayoutManager(this)

        fetchData()
    }

    private fun fetchData(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = try{
                URL("https://dojo-recipes.herokuapp.com/people/").readText(Charsets.UTF_8)
            }catch (e: Exception){
                Log.d("tsttt", e.toString())
                ""
            }
            if(response.isNotEmpty())
                setData(response)
        }
    }

    private suspend fun setData(response: String){
        withContext(Dispatchers.Main){
            val json = JSONArray(response)
            for (i in 0 until json.length()){
                names.add(json.getJSONObject(i).getString("name"))
            }
            adapter.notifyDataSetChanged()
        }
    }
}