package com.example.twolabkotlin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twolabkotlin.databinding.ActivityMain2Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity2 : AppCompatActivity() {

    val apiKey = "16155bc39940911c7c6ee305451d6362"
    val baseUrl = "http://api.openweathermap.org/"

    lateinit var binding: ActivityMain2Binding
    private lateinit var adapter: MyAdapterRec

    val citys = listOf(
        "Hong Kong",
        "Bangkok",
        "London",
        "Macau",
        "Paris",
        "Dubai",
        "New York",
        "Paris",
        "Moscow",
        "Istanbul"
    )

    val listCityTemp = mutableListOf<CitysAndTemp>(
        CitysAndTemp("Test", "Mmm: 100$"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

//        val rv = findViewById<RecyclerView>(R.id.myRecycleView)
//        rv.layoutManager = LinearLayoutManager(this)
//        adapter = MyAdapterRec()
//        rv.adapter = adapter
//        adapter.list = listCityTemp

        getWeatherList(this)

    }

    fun getWeatherList(context: Context) {

        for (i in citys) {

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service: Service = retrofit.create(Service::class.java)

            val call: Call<ResponseWe3> =
                service.getSityWeather(i, apiKey) as Call<ResponseWe3>
            call.enqueue(object : Callback<ResponseWe3?> {
                override fun onResponse(
                    call: Call<ResponseWe3?>,
                    response: Response<ResponseWe3?>
                ) {
                    if (response.code() == 200) {
                        val weatherResponse: ResponseWe3 = response.body()!!
                        val graus = weatherResponse.main!!.temp.toDouble() - 273.15
                        val weatherNow = "Temperature: $graus"
                        listCityTemp.add(CitysAndTemp(i, weatherNow))

                        val rv = findViewById<RecyclerView>(R.id.myRecycleView)
                        rv.layoutManager = LinearLayoutManager(context)
                        adapter = MyAdapterRec()
                        rv.adapter = adapter
                        adapter.list = listCityTemp

                    }
                }

                override fun onFailure(call: Call<ResponseWe3?>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }
            })

        }
    }


}