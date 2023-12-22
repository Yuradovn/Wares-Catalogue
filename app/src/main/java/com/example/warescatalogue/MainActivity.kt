package com.example.warescatalogue

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.warescatalogue.adapter.WareAdapter
import com.example.warescatalogue.database.AppDatabase
import com.example.warescatalogue.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener() {
            val intent = Intent(this, AdditionActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            val wareList = AppDatabase(this@MainActivity).getWareDao().getAllWares()

            binding.recyclerView.adapter = WareAdapter().apply {
                setDataset(wareList)

                setOnDeleteListener {
                    lifecycleScope.launch {
                        AppDatabase(this@MainActivity).getWareDao().deleteWare(it)

                        val newWareList = AppDatabase(this@MainActivity).getWareDao().getAllWares()
                        binding.recyclerView.adapter = WareAdapter().apply {
                            setDataset(newWareList)
                        }
                    }
                }
            }
        }
    }
}
