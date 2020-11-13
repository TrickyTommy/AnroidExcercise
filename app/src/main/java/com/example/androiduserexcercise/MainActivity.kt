package com.example.androiduserexcercise

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androiduserexcercise.adapter.AdapterData
import com.example.androiduserexcercise.databinding.ActivityMainBinding
import com.example.androiduserexcercise.model.DataModel
import com.example.androiduserexcercise.util.Utildata
import retrofit2.Response
import retrofit2.Call

import java.net.CacheResponse
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { AdapterData(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvProduct.adapter = adapter

        Utildata.service.getAllProduct().enqueue(object : Callback<List<DataModel>>{
            override fun onResponse(
                call: Call<List<DataModel>>,
                response: Response<List<DataModel>>

            ){
                if (response.isSuccessful){
                    response.body()?.let { adapter.setData(it) }
                    Toast.makeText(this@MainActivity, "berhasil", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this@MainActivity, "gagal rekl", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                t.printStackTrace()

                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}