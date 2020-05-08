package com.example.linkdevtask.ui

import android.app.SearchManager
import android.content.Context
import android.content.Context.*
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.GravityCompat
import androidx.core.view.MenuItemCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.linkdevtask.R
import com.example.linkdevtask.listeners.DrawerBehaviour
import com.example.linkdevtask.ui.home.HomeScreenFragment
import com.google.android.material.navigation.NavigationView
import leakcanary.AppWatcher
import leakcanary.ObjectWatcher


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    DrawerBehaviour {
    private lateinit var mToolbar: Toolbar
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mToggle: ActionBarDrawerToggle
    private lateinit var mNavigationView: NavigationView
    private lateinit var errorView:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews(savedInstanceState)
    }
    private fun initViews(savedInstanceState: Bundle?) {
        mToolbar = findViewById(R.id.toolbar)
        errorView=findViewById(R.id.empty_view)
        mDrawerLayout = findViewById(R.id.drawer_layout)
        mNavigationView = findViewById(R.id.nav_view)
        setSupportActionBar(mToolbar)
        mNavigationView.setNavigationItemSelectedListener(this)
        mToggle = ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.nav_drawer_open, R.string.nav_drawer_close)
        mToggle.syncState()
        mDrawerLayout.addDrawerListener(mToggle)
        //Switching between back button and hamburger while navigation
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                // show back button
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                mToolbar.setNavigationOnClickListener { onBackPressed() }
            } else {
                //show hamburger
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
                mToggle.syncState()
                mToolbar.setNavigationOnClickListener { mDrawerLayout.openDrawer(GravityCompat.START) }
            }
        }
        if (savedInstanceState == null && isNetworkAvailable()) {
            val mHomeScreenFragment = HomeScreenFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, mHomeScreenFragment)
                .commit()
        }
        else{
            errorView.visibility = View.VISIBLE
        }
    }
    //check the network state
    @Suppress("DEPRECATION")
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    override fun lockDrawer() {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun unlockDrawer() {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.explore_nav -> {
                Toast.makeText(this, "Explore", Toast.LENGTH_SHORT).show()
            }
            R.id.live_nav -> {
                Toast.makeText(this, "Live Chat", Toast.LENGTH_SHORT).show()
            }
            R.id.gallery_nav -> {
                Toast.makeText(this, "Gallery", Toast.LENGTH_SHORT).show()
            }
            R.id.wish_list_nav -> {
                Toast.makeText(this, "Wish List", Toast.LENGTH_SHORT).show()
            }
            R.id.magazine_nav -> {
                Toast.makeText(this, "E-Magazine", Toast.LENGTH_SHORT).show()
            }
        }
        mDrawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    @Suppress("DEPRECATION")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.right_menu, menu)
        val searchView =
            MenuItemCompat.getActionView(menu.findItem(R.id.action_search)) as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnSearchClickListener {
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onBackPressed() {
        when {
            mDrawerLayout.isDrawerOpen(GravityCompat.START) -> mDrawerLayout.closeDrawer(GravityCompat.START)
            supportFragmentManager.backStackEntryCount > 0 -> supportFragmentManager.popBackStack()
            else -> super.onBackPressed()
        }

    }
}
