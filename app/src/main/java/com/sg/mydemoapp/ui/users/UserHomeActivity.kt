package com.sg.mydemoapp.ui.users

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.sg.mydemoapp.R
import com.sg.mydemoapp.databinding.ActivityUserHomeBinding
import com.sg.mydemoapp.ui.users.profile.UserProfileFragment
import com.sg.mydemoapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserHomeBinding

    companion object {
        var userIdHome = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbar)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_user_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_user_home, R.id.navigation_user_photos, R.id.navigation_user_profile
            )
        )
        val id = intent.getIntExtra(Constants.EXTRA_USER_ID, 0)
        userIdHome = id
        val bundle = Bundle()
        bundle.putInt(Constants.EXTRA_USER_ID, id)
        navController.setGraph(
            R.navigation.mobile_navigation,
            bundle
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}