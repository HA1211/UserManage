package com.nqh.usermanage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nqh.usermanage.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {


    lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarSignIn)

        supportActionBar?.title = "SIGN IN"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbarSignIn.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}