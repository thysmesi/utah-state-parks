package com.example.utahstateparks

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.utahstateparks.data.AppDatabase
import com.example.utahstateparks.data.StatePark
import com.example.utahstateparks.databinding.ActivityMainBinding
import com.example.utahstateparks.databinding.HomeFragmentBinding
import com.example.utahstateparks.databinding.MapFragmentBinding
import com.example.utahstateparks.utilities.PARKS_DATA_FILENAME
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navView: NavigationView
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        checkFirstRun()

        navView = binding.navView
        drawerLayout = binding.drawerLayout
        navController = findNavController(R.id.nav_host_fragment_content_main)


        val lifecycleOwner = this
        val database = AppDatabase.getInstance(applicationContext)
        val favorites = database.stateParkDao().getFavorites()

        favorites.observe(lifecycleOwner, Observer {
            navView.menu.removeGroup(2)
            val menuGroup = navView.menu.addSubMenu(2,0,0,"Favorite Parks")

            favorites.value?.forEach {
                val item = menuGroup.add(it.parkName)

                item.setOnMenuItemClickListener { menuItem ->
                    val bundle = bundleOf("stateParkKey" to it.parkId)

                    navController.navigate(R.id.stateParkFragment, bundle)
                    drawerLayout.closeDrawers()

                    true
                }
            }
        })

        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.homeFragment, R.id.contactFragment, R.id.passInfoFragment, R.id.mapFragment, R.id.parkSelectorFragment, R.id.stateParkFragment), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun checkFirstRun() {
        val sharedPrefs = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
        val firstRun = sharedPrefs.getBoolean("first_run", true)

        if(firstRun) {
            lifecycleScope.launch {
                applicationContext.assets.open(PARKS_DATA_FILENAME).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val stateParkType = object : TypeToken<List<StatePark>>() {}.type
                        val stateParkList: List<StatePark> = Gson().fromJson(jsonReader, stateParkType)

                        val database = AppDatabase.getInstance(applicationContext)
                        database.stateParkDao().insertAll(stateParkList)
                    }
                }
            }

            sharedPrefs.edit().putBoolean("first_run", false).apply()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}