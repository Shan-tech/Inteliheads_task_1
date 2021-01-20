package com.example.inteliheads.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.inteliheads.R
import com.example.inteliheads.fragment.Category
import com.example.inteliheads.fragment.Product
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var btmNavg: BottomNavigationView
    lateinit var frameLayout: FrameLayout
    private lateinit var toolbar: Toolbar
    private var previousMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Dashboard"

        btmNavg = findViewById(R.id.bottom_nav_view)
        frameLayout = findViewById(R.id.frameLayout)
        val bottomNavigationView = BottomNavigationView.OnNavigationItemSelectedListener {
            if (previousMenuItem != null) {
                previousMenuItem?.isChecked = false
                it.isChecked = true
            }
            it.isCheckable = true
            previousMenuItem = it
            when (it.itemId) {
                R.id.pdt -> {
                    supportActionBar?.title = "Product"

                    replaceFragment(Product())
                    true
                }
                R.id.category -> {
                    supportActionBar?.title = "Category"
                    replaceFragment(Category())

                    true
                }
                else -> false
            }

        }
        btmNavg.setOnNavigationItemSelectedListener(bottomNavigationView)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragTransaction = supportFragmentManager.beginTransaction()
        fragTransaction.replace(R.id.frameLayout, fragment)
        fragTransaction.addToBackStack(null)
        fragTransaction.commit()
    }



}