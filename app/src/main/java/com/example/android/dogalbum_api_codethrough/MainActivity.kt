package com.example.android.dogalbum_api_codethrough

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.net.toUri
import coil.load
import com.example.android.dogalbum_api_codethrough.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: DogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getRandomDogPhoto()
        showPhotoByBreed()
    }
    private fun getRandomDogPhoto() {
        val randomPhotoButton = binding.button

        viewModel.dogPhoto.observe(this, {
            val imgUri = it.imageUrl!!.toUri().buildUpon().scheme("https").build()
            binding.imageView.load(imgUri)
        })
        randomPhotoButton.setOnClickListener {
            viewModel.getNewPhoto()
        }
    }
    private fun showPhotoByBreed(){
        val search = binding.searchButton
        val userInput = binding.etSearchTerm

        search.setOnClickListener {
            if (userInput.text.isNotBlank()) {
                viewModel.getPhotoByBreed(userInput.text.toString())
            }else {
                Snackbar
                .make(this,
                    userInput,
                    "Please enter a dog's breed!",
                    Snackbar.LENGTH_SHORT)
                .show()

            }
        }
    }
}