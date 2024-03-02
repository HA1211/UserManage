package com.nqh.usermanage.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.nqh.usermanage.R
import com.nqh.usermanage.databinding.ActivityMainBinding


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var binding: ActivityMainBinding

    val toolbarMain = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
        binding.navView.setNavigationItemSelectedListener(this)
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbarMain)
        toolbarMain.setNavigationIcon(R.drawable.ic_navigation_menu)

        toolbarMain.setNavigationOnClickListener {
            toggleDrawer()
        }
    }

    private fun toggleDrawer(){
        if(binding.drawerLayoutMainActivity.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayoutMainActivity.closeDrawer(GravityCompat.START)
        }else{
            binding.drawerLayoutMainActivity.openDrawer(GravityCompat.START)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_profile -> {
                Toast.makeText(this@MainActivity, "Profile", Toast.LENGTH_SHORT).show()
            }

            R.id.navigation_sign_out -> {
                FirebaseAuth.getInstance().signOut()

                val intent = Intent(this, IntroActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }
        binding.drawerLayoutMainActivity.closeDrawer(GravityCompat.START)
        return true
    }
}