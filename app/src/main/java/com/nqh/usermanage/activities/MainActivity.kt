package com.nqh.usermanage.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.nqh.usermanage.R
import com.nqh.usermanage.databinding.ActivityMainBinding


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setupActionBar()
    }

    private fun setupActionBar(){
        val toolbarMain = findViewById<Toolbar>(R.id.toolbar_main)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
         setSupportActionBar(toolbarMain)

        toolbarMain.setNavigationIcon(R.drawable.ic_navigation_menu)

        toolbarMain.setNavigationOnClickListener {
            if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.closeDrawer(GravityCompat.START)
            }else{
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navigation_profile -> {
                Toast.makeText(this@MainActivity, "Profile", Toast.LENGTH_SHORT).show()
            }
            R.id.navigation_sign_out -> {
                FirebaseAuth.getInstance().signOut()

                val intent = Intent(this, IntroActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }
    }
}