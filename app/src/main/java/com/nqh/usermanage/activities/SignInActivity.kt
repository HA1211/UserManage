package com.nqh.usermanage.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.nqh.usermanage.R
import com.nqh.usermanage.databinding.ActivitySignInBinding
import com.nqh.usermanage.firebase.FirestoreClass
import com.nqh.usermanage.models.User

class SignInActivity : BaseActivity() {

    lateinit var binding: ActivitySignInBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        setSupportActionBar(binding.toolbarSignIn)

        supportActionBar?.title = "SIGN IN"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbarSignIn.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.btnSignIn.setOnClickListener {
            signInRegisteredUser()
        }
    }

    fun signInSuccess(user: User) {
        hideProgressDialog()
        startActivity(Intent(this@SignInActivity, MainActivity::class.java))
        this.finish()
    }

    private fun signInRegisteredUser(
        email: String = binding.etEmailSignIn.text.toString().trim{it <= ' '},
        password: String = binding.etPasswordSignIn.text.toString().trim{it <= ' '}
    ){
        if(validateForm(email, password)){
            showProgressDialog(resources.getString(R.string.please_wait))
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        FirestoreClass().signInUser(this@SignInActivity)
                        startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                    } else {
                        Log.w("hiep", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }
    }

    private fun validateForm(email: String, password: String): Boolean{
        return when{
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