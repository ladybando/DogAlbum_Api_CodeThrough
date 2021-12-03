package com.example.android.dogalbum_api_codethrough.application

import android.app.Application
import com.example.android.dogalbum_api_codethrough.data.ImageDatabase

class ImageApplication : Application() {
    val database: ImageDatabase by lazy { ImageDatabase.getDatabase(this) }
}