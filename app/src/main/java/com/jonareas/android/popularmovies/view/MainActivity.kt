package com.jonareas.android.popularmovies.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.jonareas.android.popularmovies.R
import com.jonareas.android.popularmovies.databinding.ActivityMainBinding
import com.jonareas.android.popularmovies.databinding.NavHeaderMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var headerBinding: NavHeaderMainBinding

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        headerBinding = NavHeaderMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(setOf(R.id.moviesViewPagerFragment,
            R.id.tvShowsViewPagerFragment,
            R.id.preferencesFragment), binding.root)

        setupNavDrawer(binding.navigationView)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    private fun setupNavDrawer(navigationView: NavigationView): Unit = navigationView.run {
        setupWithNavController(navController)
        addHeaderView(headerBinding.root)
        setCheckedItem(navController.graph.startDestinationId)
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

}
