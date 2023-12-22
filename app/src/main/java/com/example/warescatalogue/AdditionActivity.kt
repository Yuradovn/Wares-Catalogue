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
    private var ware: Ware? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdditionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ware = intent.getSerializableExtra("Ware") as Ware?

        if (null === ware) {
            binding.buttonPersist.text = getString(R.string.create_ware_button)
        } else {
            binding.buttonPersist.text = getString(R.string.update_button_text)
            binding.editName.setText(ware?.name)
            binding.editDescription.setText(ware?.description)
            binding.editPrice.setText(String.format("%.2f", (ware!!.price.toFloat() / 100)))
        }

        binding.buttonPersist.setOnClickListener { addWare() }
    }

    private fun addWare() {
        val name = binding.editName.text.toString()
        val description = binding.editDescription.text.toString()
        val price = (binding.editPrice.text.toString().toDouble() * 100).roundToInt()

        lifecycleScope.launch {
            if (null === ware) {
                val ware = Ware(name = name, description = description, price = price)
                AppDatabase(this@AdditionActivity).getWareDao().addWare(ware)
            } else {
                val editedWare = Ware(name, description, price)
                editedWare.id = ware?.id ?: 0
                AppDatabase(this@AdditionActivity).getWareDao().updateWare(editedWare)
            }

            finish()
        }
    }
}