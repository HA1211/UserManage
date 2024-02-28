package com.nqh.usermanage.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nqh.usermanage.R
import com.nqh.usermanage.databinding.ActivityMainBinding


class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    private fun setupActionBar(){
        setSupportActionBar(binding.)
    }

}