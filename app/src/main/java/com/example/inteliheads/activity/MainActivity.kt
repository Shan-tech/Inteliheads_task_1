package com.example.inteliheads.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.inteliheads.R
import com.example.inteliheads.fragment.category
import com.example.inteliheads.fragment.Product
import com.google.android.material.bottomnavigation.BottomNavigationView
                                                    //Toolbar shd be fixed
class MainActivity : AppCompatActivity() {
    lateinit var btmNavg: BottomNavigationView
    //lateinit var toolbar:Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Dashboard"
        supportActionBar?.hide()

        //toolbar = findViewById(R.id.toolbar)
       // setToolbar()
        btmNavg = findViewById(R.id.bottom_nav_view)
        val bottomNavigationView = BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.pdt -> {
                    replaceFragment(Product())
                    supportActionBar?.title = "Product"

                    true
                }

                R.id.category -> {
                    replaceFragment(category())
                    supportActionBar?.title = "Category"


                    true
                }
                else -> false
            }

        }
        btmNavg.setOnNavigationItemSelectedListener(bottomNavigationView)
    }

    fun replaceFragment(fragment: Fragment) {
        val fragTransaction = supportFragmentManager.beginTransaction()
        //setSupportActionBar(toolbar)
        fragTransaction.replace(R.id.frameLayout, fragment)
        fragTransaction.commit()
    }

    /*fun setToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Dashboard"
    }*/
}