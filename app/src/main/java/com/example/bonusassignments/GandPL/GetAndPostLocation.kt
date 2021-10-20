package com.example.bonusassignments.GandPL

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bonusassignments.R
import com.example.bonusassignments.SPR.MyDataItem
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetAndPostLocation : AppCompatActivity() {

    private lateinit var result: TextView
    private lateinit var searchName: EditText
    private lateinit var name: EditText
    private lateinit var location: EditText
    private lateinit var fab: ExtendedFloatingActionButton
    private lateinit var searchBtn: Button

    private val apiInterface = ApiClient().getClient()?.create(ApiInterface::class.java)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_and_post_location)

        result = findViewById(R.id.get_and_post_location_location)
        searchBtn  = findViewById(R.id.get_and_post_location_btn_search)
        fab = findViewById(R.id.get_and_post_location_fab)
        name = findViewById(R.id.get_and_post_location_ed_name)
        searchName = findViewById(R.id.get_and_post_location_ed_search)
        location = findViewById(R.id.get_and_post_location_ed_location)

        fab.setOnClickListener {
            if(name.text.isNotEmpty() && location.text.isNotEmpty())
                addPerson()
            else
                Toast.makeText(this, "Please enter the name you want to add", Toast.LENGTH_LONG).show()
        }

        searchBtn.setOnClickListener {
            if(searchName.text.isNotEmpty()){
                apiInterface!!.getData().enqueue(object : Callback<List<com.example.bonusassignments.GandPL.MyDataItem>?> {
                    override fun onResponse(
                        call: Call<List<com.example.bonusassignments.GandPL.MyDataItem>?>,
                        response: Response<List<com.example.bonusassignments.GandPL.MyDataItem>?>
                    ) {
                        for (i in response.body()!!)
                            if (i.name == searchName.text.toString()){
                                result.text = i.location
                                break
                            }
                        if(result.text.isEmpty())
                            result.text = "Can't find any person with the same name"
                    }

                    override fun onFailure(
                        call: Call<List<com.example.bonusassignments.GandPL.MyDataItem>?>,
                        t: Throwable
                    ) {
                        Toast.makeText(this@GetAndPostLocation, "Error occur", Toast.LENGTH_LONG).show()
                    }
                })
            }
            else
                Toast.makeText(this, "Please enter the name you want to know his location", Toast.LENGTH_LONG).show()
        }

    }

    private fun addPerson() {
        apiInterface!!.addName(MyDataItem(name.text.toString(), location.text.toString(), -1)).enqueue(object : Callback<com.example.bonusassignments.GandPL.MyDataItem?> {
            override fun onResponse(call: Call<com.example.bonusassignments.GandPL.MyDataItem?>, response: Response<com.example.bonusassignments.GandPL.MyDataItem?>) {
                Toast.makeText(this@GetAndPostLocation, "Added successfully", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<com.example.bonusassignments.GandPL.MyDataItem?>, t: Throwable) {
                Toast.makeText(this@GetAndPostLocation, "Error occur", Toast.LENGTH_LONG).show()
            }
        })
    }
}