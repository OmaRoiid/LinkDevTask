package com.example.linkdevtask.ui
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.example.linkdevtask.R
import com.example.linkdevtask.util.showToast
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    private lateinit var mAppBarConfiguration: AppBarConfiguration
    private lateinit var mNavController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupNavController()
    }
    private fun setupNavController(){
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment)
        mAppBarConfiguration = AppBarConfiguration(mNavController.graph, drawer_layout)
        NavigationUI.setupActionBarWithNavController(this, mNavController, drawer_layout)
        NavigationUI.setupWithNavController(nav_view, mNavController)
        nav_view.setNavigationItemSelectedListener(this)
        // Setup Drawer Behaviour
        mNavController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeScreenFragment -> {
                    drawer_layout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                }
                else -> {
                    drawer_layout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.nav_host_fragment)
            .navigateUp(mAppBarConfiguration)|| super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        showToast(item.title.toString())
        return true
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.right_menu, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView=menuItem.actionView as SearchView
        searchView.setOnSearchClickListener {
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
        }
        return true
    }
    override fun onBackPressed() = when {
        drawer_layout.isDrawerOpen(GravityCompat.START) -> drawer_layout.closeDrawer(GravityCompat.START)
        else -> super.onBackPressed()
    }
}
