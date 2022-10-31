package com.rec.comprehendrelationship.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.rec.comprehendrelationship.R
import com.rec.comprehendrelationship.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.ivStartLogo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.am_smallbigforth))
        binding.ivStartLogo.animate().alpha(1f).duration = 1600

        binding.start.setOnClickListener {
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // val intent = Intent()
    }
}