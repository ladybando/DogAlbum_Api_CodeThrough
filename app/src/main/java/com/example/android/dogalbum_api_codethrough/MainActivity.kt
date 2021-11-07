package com.example.android.dogalbum_api_codethrough

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.net.toUri
import coil.load
import com.example.android.dogalbum_api_codethrough.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: DogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
    /**
     * Inflates the layout with View Binding, and calls [getRandomDogPhoto] method.
     */
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getRandomDogPhoto()
    }
    fun getRandomDogPhoto() {
        val randomPhotoButton = binding.button

        /*Observes dogPhoto from View Model and uses the Coil library
        * to load an image into an [ImageView].*/
        viewModel.dogPhoto.observe(this, {
            val imgUri = it.imageUrl!!.toUri().buildUpon().scheme("https").build()
            binding.imageView.load(imgUri)
        })
        /* Calls [getNewPhoto] method from View Model when [Button] is selected.*/
        randomPhotoButton.setOnClickListener {
            viewModel.getNewPhoto()
        }
    }

}
