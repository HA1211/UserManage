package com.nqh.usermanage.activities

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.nqh.usermanage.databinding.ActivitySignUpBinding
import com.nqh.usermanage.firebase.FirestoreClass
import com.nqh.usermanage.models.User


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
        binding.btnSignUp.setOnClickListener {
            registerUser()
        }
    }

    fun userRegisteredSuccess(){
        Toast.makeText(this,"You have successful with email", Toast.LENGTH_LONG).show()
        hideProgressDialog()
        FirebaseAuth.getInstance().signOut()
        finish()
    }


    private fun registerUser(
        name: String = binding.etName.text.toString().trim{it <= ' '},
        email: String = binding.etEmail.text.toString().trim{it <= ' '},
        password: String = binding.etPassword.text.toString().trim{it <= ' '}
    ){
        if(validateForm(name, email, password)){
            showProgressDialog("Please Wait")
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {task ->
                hideProgressDialog()
                if(task.isSuccessful){
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    val registeredEmail = firebaseUser.email!!
                    val user = User(firebaseUser.uid, name, registeredEmail)

                    FirestoreClass().registerUser(this, user)
                }else{
                    Toast.makeText(this,"Registration failed", Toast.LENGTH_SHORT).show()
                }
            }
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