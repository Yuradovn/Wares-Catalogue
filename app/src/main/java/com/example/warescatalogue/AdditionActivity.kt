package com.example.warescatalogue

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.warescatalogue.database.AppDatabase
import com.example.warescatalogue.databinding.ActivityAdditionBinding
import com.example.warescatalogue.entity.Ware
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class AdditionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdditionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdditionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener { addWare() }
    }

    private fun addWare() {
        val name = binding.name.text.toString()
        val description = binding.description.text.toString()
        val price = (binding.price.text.toString().toDouble() * 100).roundToInt()

        lifecycleScope.launch {
            val ware = Ware(name = name, description = description, price = price)
            AppDatabase(this@AdditionActivity).getWareDao().addWare(ware)

            finish()
        }
    }
}