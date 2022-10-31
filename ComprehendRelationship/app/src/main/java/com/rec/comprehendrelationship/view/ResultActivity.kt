package com.rec.comprehendrelationship.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rec.comprehendrelationship.R
import com.rec.comprehendrelationship.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val correctAnsNo = intent.getStringExtra("correct")
        val totalAnsNo = intent.getStringExtra("total")

        binding.correctAns.text = correctAnsNo
        binding.totalAns.text = totalAnsNo

        val percentage = (correctAnsNo?.toFloat()?.div(totalAnsNo?.toFloat()!!))?.times(100)
        if (percentage != null) {
            when {
                50 <= percentage && percentage <= 99 -> {
                    binding.performance.text = "Хорошо"
                    binding.output.background = getDrawable(R.drawable.bg_validate_empty)
                }

                percentage>=100 -> {
                    binding.performance.text = "Превосходно"
                    binding.output.background = getDrawable(R.drawable.bg_validate_right)
                }

                percentage<50 -> {
                    binding.performance.text = "Недостаточный"
                    binding.output.background = getDrawable(R.drawable.bg_validate_wrong)
                }
            }

            binding.btnRest.setOnClickListener {
                val intent= Intent(this, StartActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }



    override fun onBackPressed() {
        val intent= Intent(this, StartActivity::class.java)
        startActivity(intent)
    }
}