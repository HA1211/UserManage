package com.nqh.usermanage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.nqh.usermanage.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity() {


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


    private fun registerUser(){
        val name: String = binding.etName.text.toString().trim{it <= ' '} //cat khoang trong
        val email: String = binding.etEmail.text.toString().trim{it <= ' '}
        val password: String = binding.etPassword.text.toString().trim{it <= ' '}

        if(validateForm(name, email, password)){
            Toast.makeText(this@SignUpActivity, "Now we can register a new user", Toast.LENGTH_SHORT).show()
        }

    }

    private fun validateForm(name: String, email: String, password: String): Boolean{
        return when{
            TextUtils.isEmpty(name)->{
                showErrorSnackBar("Please enter a name")
                false
            }
            TextUtils.isEmpty(email)->{
                showErrorSnackBar("Please enter an email")
                false
            }
            TextUtils.isEmpty(password)->{
                showErrorSnackBar("Please enter a password")
                false
            }else->{
                return true
            }
        }
    }

}