package com.example.android.dogalbum_api_codethrough

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.android.dogalbum_api_codethrough.network.DogPhoto
import com.example.android.dogalbum_api_codethrough.network.DogPhotoApi

class DogViewModel : ViewModel() {
    
    // The internal MutableLiveData that stores the status of the most recent request
    private val _dogPhoto = MutableLiveData<DogPhoto>()
    // The external immutable LiveData for the request status
    val dogPhoto: LiveData<DogPhoto> = _dogPhoto

        /**
     * Call getNewPhoto() on init so we can display status immediately.
     */
    init {
        getNewPhoto()
    }

     /**
     * Gets dog photos information from the Dog API Retrofit service
     */
    fun getNewPhoto() {
        viewModelScope.launch {
            _dogPhoto.value = DogPhotoApi.retrofitService.getRandomPhoto()
        }
    }

}
