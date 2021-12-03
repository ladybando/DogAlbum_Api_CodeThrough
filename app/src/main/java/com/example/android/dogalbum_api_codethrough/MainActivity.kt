package com.example.android.dogalbum_api_codethrough

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.net.toUri
import coil.load
import com.example.android.dogalbum_api_codethrough.application.ImageApplication
import com.example.android.dogalbum_api_codethrough.data.ImageEntity
import com.example.android.dogalbum_api_codethrough.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: DogViewModel by viewModels{
        DogViewModelFactory((application as ImageApplication).database.imageDao())
    }

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
            val currentImage = viewModel.dogPhoto.value!!.imageUrl
            val previousImage = currentImage?.let { dogImage -> ImageEntity(imageUrl = dogImage) }
            viewModel.getNewPhoto()
            if(previousImage != null){
                viewModel.insertNewImage(previousImage)
            }
            viewModel.deleteLastImage()
        }

        binding.lastPicButton.setOnClickListener {
            val intent = Intent(this, LastImageActivity::class.java)
            startActivity(intent)
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