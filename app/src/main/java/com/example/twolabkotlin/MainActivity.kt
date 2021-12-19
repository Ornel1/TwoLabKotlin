package com.example.twolabkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.twolabkotlin.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val apiKey = "16155bc39940911c7c6ee305451d6362"
    val baseUrl = "http://api.openweathermap.org/"

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getNowBtn.setOnClickListener {
            weatherNow()
        }

        binding.getListBtn.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

    }


    fun weatherNow() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: Service = retrofit.create(Service::class.java)

        val call: Call<ResponseWe3> = service.getSityWeather("Moscow", apiKey) as Call<ResponseWe3>
        call.enqueue(object : Callback<ResponseWe3?> {
            override fun onResponse(
                call: Call<ResponseWe3?>,
                response: Response<ResponseWe3?>
            ) {
                if (response.code() == 200) {
                    val weatherResponse: ResponseWe3 = response.body()!!
                    val graus = weatherResponse.main!!.temp.toDouble() - 273.15
                    val weatherNow = "Temperature: $graus"

                    binding.textView.text = weatherNow
                }
            }

            override fun onFailure(call: Call<ResponseWe3?>, t: Throwable) {
                binding.textView.text = t.message
                Log.d("TAG", t.message.toString())
            }
        })
    }


}