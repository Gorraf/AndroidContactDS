package fr.isen.pecorella.androidcontactds

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.pecorella.androidcontactds.databinding.ActivityMainBinding
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var url = "https://randomuser.me/api/"
    private lateinit var category: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = intent.getStringExtra("Category") ?: ""
        this.title = category
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = Adapter(arrayListOf()) {
            startActivity(intent)
        }

        loadContactFromAPI()
    }


    private fun loadContactFromAPI() {

        val jsonObject = JSONObject()
        jsonObject.put("results", 10)


        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, jsonObject,
            {
                Log.w("MainActivity","response : $it")
                handleAPIData(it.toString())
            },
            {
                Log.e("API Error", it.toString())
            })
        Volley.newRequestQueue(this).add(jsonRequest)
    }

    private fun handleAPIData(data: String){
        val contactResult = Gson().fromJson(data, DataFile::class.java)
        val nameCategory = contactResult.results.firstOrNull { it.gender == category }

        val adapter = binding.recyclerView.adapter as Adapter
        adapter.refreshList( nameCategory?.name as ArrayList<Results> )
    }

}
