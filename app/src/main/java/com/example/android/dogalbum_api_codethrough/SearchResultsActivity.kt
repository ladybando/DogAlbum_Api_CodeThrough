package com.example.android.dogalbum_api_codethrough

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.media.session.MediaButtonReceiver.handleIntent
import com.example.android.dogalbum_api_codethrough.databinding.ActivitySearchResultsBinding
import com.example.android.dogalbum_api_codethrough.model.DogViewModel
import com.google.android.material.snackbar.Snackbar

class SearchResultsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchResultsBinding
    private val viewModel: DogViewModel by viewModels()
    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleIntent(intent)
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem)= when(item.itemId) {
        R.id.app_bar_search -> {
            handleIntent(intent)
            true
        }
        R.id.favorite -> {
            Toast.makeText(this, "You added a favorite!", Toast.LENGTH_SHORT).show()
            true
        }
        else ->{
            super.onOptionsItemSelected(item)
        }
    }
    private fun handleIntent(intent: Intent?){
        if(Intent.ACTION_SEARCH == intent!!.action){
            val userQuery = intent.getStringExtra(SearchManager.QUERY)
            searchView.setOnSearchClickListener {
                viewModel.getPhotoByBreed(userQuery)
            }
        }
    }
}