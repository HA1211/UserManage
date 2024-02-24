package com.nqh.usermanage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nqh.usermanage.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {


    lateinit var binding:ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarSignUp)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "SIGN UP"

        binding.toolbarSignUp.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}