package com.example.android.dogalbum_api_codethrough


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.android.dogalbum_api_codethrough.databinding.ActivityMainBinding
import com.example.android.dogalbum_api_codethrough.model.DogViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: DogViewModel by viewModels()
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val search = menu.findItem(R.id.app_bar_search)
        searchView = search.actionView as SearchView

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (searchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(userQuery: String?): Boolean {
                //Called when this view wants to give up focus
                searchView.clearFocus()
                //Returns the query string currently in the text field. checks if string is empty?
                searchView.setQuery("", false)
                search.collapseActionView()
                viewModel.getPhotoByBreed(userQuery)
                return true
            }
            override fun onQueryTextChange(userInput: String?): Boolean {
                return false
            }
        })
        return true
    }

}