package com.example.android.dogalbum_api_codethrough

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.android.dogalbum_api_codethrough.network.DogPhoto
import com.example.android.dogalbum_api_codethrough.network.DogPhotoApi

class DogViewModel : ViewModel() {
    private val _dogPhoto = MutableLiveData<DogPhoto>()
    val dogPhoto: LiveData<DogPhoto> = _dogPhoto

    init {
        getNewPhoto()
    }

    fun getNewPhoto() {
        viewModelScope.launch {
            _dogPhoto.value = DogPhotoApi.retrofitService.getRandomPhoto()
        }
    }

}