package com.example.bonusassignments.SPR

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.GridView
import android.widget.Toast
import com.example.bonusassignments.R
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SimplePOSTRequest : AppCompatActivity() {

    private lateinit var names: MutableList<MyDataItem>
    private lateinit var grid: GridView
    private lateinit var adapter: GridViewAdapter
    private lateinit var fab: ExtendedFloatingActionButton
    private lateinit var inputName: EditText

    val apiInterface = ApiClient().getClient()?.create(ApiInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_p_o_s_t_request)

        names = mutableListOf()
        grid = findViewById(R.id.grid_view_display)
        fab = findViewById(R.id.simple_post_request_fab)
        inputName = findViewById(R.id.simple_post_request_ed_name)
        adapter = GridViewAdapter(names, this)
        grid.adapter = adapter



        fetchData()

        fab.setOnClickListener{
            if(inputName.text.isNotEmpty())
                addPerson()
            else
                Toast.makeText(this, "Please enter the name you want to add", Toast.LENGTH_LONG).show()
        }
    }

    private fun addPerson() {
        apiInterface!!.addName(MyDataItem(inputName.text.toString(), -1)).enqueue(object : Callback<MyDataItem?> {
            override fun onResponse(call: Call<MyDataItem?>, response: Response<MyDataItem?>) {
                Toast.makeText(this@SimplePOSTRequest, "Added successfully", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<MyDataItem?>, t: Throwable) {
                Toast.makeText(this@SimplePOSTRequest, "Error occur", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun fetchData(){
            apiInterface!!.getData().enqueue(object : Callback<List<MyDataItem>?> {
                override fun onResponse(
                    call: Call<List<MyDataItem>?>,
                    response: Response<List<MyDataItem>?>
                ) {
                    val l = response.body()!!
                    for(i in l){
                        names.add(i)
                    }
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                    Toast.makeText(this@SimplePOSTRequest, "Error occur", Toast.LENGTH_LONG).show()
                }
            })
    }
}