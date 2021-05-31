package com.example.ciromine.movieappv3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.ciromine.movieappv3.R
import com.example.ciromine.movieappv3.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var navHostFragment = NavHostFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupActivity()
    }

    private fun setupActivity() {
        setUpNavigation()
        destinationManager()
    }

    private fun setUpNavigation() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navGraph = navHostFragment.navController.graph
        navHostFragment.navController.setGraph(navGraph, intent.extras)
        appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.movieListFragment,
                R.id.detailFragment
            )
            .build()

        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)
    }

    private fun destinationManager() {
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.movieListFragment -> {
                    supportActionBar?.apply {
                        setDisplayHomeAsUpEnabled(true)
                        setDisplayShowHomeEnabled(true)
                        show()
                    }
                }

                R.id.detailFragment -> {
                    supportActionBar?.apply {
                        setDisplayHomeAsUpEnabled(true)
                        setDisplayShowHomeEnabled(true)
                        show()
                    }
                }
                else -> {
                    supportActionBar?.show()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}