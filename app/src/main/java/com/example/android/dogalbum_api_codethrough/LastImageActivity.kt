package com.example.android.dogalbum_api_codethrough

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import coil.load
import com.example.android.dogalbum_api_codethrough.application.ImageApplication
import com.example.android.dogalbum_api_codethrough.databinding.ActivityLastImageBinding
import com.example.android.dogalbum_api_codethrough.databinding.ActivityMainBinding

class LastImageActivity : AppCompatActivity() {
    private val viewModel: DogViewModel by viewModels{
       DogViewModelFactory((application as ImageApplication).database.imageDao())
    }
    private lateinit var binding: ActivityLastImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLastImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getAllImagesList().observe(this,{ dogImage ->
            val prevImage = binding.previousDogImageView
            prevImage.load(dogImage[0].imageUrl)
            binding.currentButton.setOnClickListener { finish() }
        })
    }
}