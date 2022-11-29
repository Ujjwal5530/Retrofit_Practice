package com.malhotra.retrofitpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.malhotra.retrofitpractice.databinding.ActivityMainBinding
import com.malhotra.retrofitpractice.network.ApiClient
import com.malhotra.retrofitpractice.network.CharacterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = ApiClient.apiService.fetchCharacters("2")

        client.enqueue(object : Callback<CharacterResponse> {

            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if(response.isSuccessful) {
                    Log.d("000000", ""+response.body())
                    val result = response.body()?.result
                    result?.let {
                        binding.charRv.adapter = Adapter(applicationContext, it)
                        binding.charRv.layoutManager =
                            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    }
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("not happen", t.message.toString())
            }

        })
    }
}