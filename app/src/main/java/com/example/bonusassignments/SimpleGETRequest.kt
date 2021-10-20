package com.example.bonusassignments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.lang.Exception
import java.net.URL

class SimpleGETRequest : AppCompatActivity() {

    private lateinit var tv: TextView
    private var res = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_g_e_t_request)

        tv = findViewById(R.id.get_request_tv_names)

        fetchData()
    }

    private fun fetchData(){
        CoroutineScope(IO).launch {
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
        withContext(Main){
            res = ""
            val json = JSONArray(response)
            for (i in 0 until json.length()){
                res += json.getJSONObject(i).getString("name") + "\n"
            }
            tv.text = res
        }
    }
}