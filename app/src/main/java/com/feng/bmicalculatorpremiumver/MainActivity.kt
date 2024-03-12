package com.feng.bmicalculatorpremiumver

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.feng.bmicalculatorpremiumver.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            if(binding.editTextHeight.text.isEmpty() || binding.editTextHeight.text.toString().toFloat() == 0.0f){
                binding.editTextHeight.setError(getString(R.string.warn_height))
                return@setOnClickListener
            }

            if(binding.editTextWeight.text.isEmpty() || binding.editTextWeight.text.toString().toFloat() == 0.0f){
                binding.editTextWeight.setError(getString(R.string.warn_weight))
                return@setOnClickListener
            }

            val height = binding.editTextHeight.text.toString().toFloat()
            val weight = binding.editTextWeight.text.toString().toFloat()

            val bmi = weight/((height/100).pow(2))

            if(bmi < 18.5){
                binding.imageViewBMI.setImageResource(R.drawable.under)
                binding.textViewBMI.text = String.format("%s : %.2f (%s)",
                    getString(R.string.bmi), bmi, getString(R.string.under))
            } else if(bmi > 25) {
                binding.imageViewBMI.setImageResource(R.drawable.over)
                binding.textViewBMI.text = String.format("%s : %.2f (%s)",
                    getString(R.string.bmi), bmi, getString(R.string.over))
            } else {
                binding.imageViewBMI.setImageResource(R.drawable.normal)
                binding.textViewBMI.text = String.format("%s : %.2f (%s)",
                    getString(R.string.bmi), bmi, getString(R.string.normal))
            }

        }

        binding.resetButton.setOnClickListener {
            binding.imageViewBMI.setImageResource(R.drawable.empty)
            binding.textViewBMI.text = getString(R.string.bmi)
            binding.editTextHeight.text.clear()
            binding.editTextWeight.text.clear()
        }

        binding.imageViewInfo.setOnClickListener{
            val intent = Intent(this, MoreInfoActivity::class.java)

            startActivity(intent)
        }
    }
}