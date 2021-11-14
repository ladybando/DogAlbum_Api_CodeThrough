package com.example.android.dogalbum_api_codethrough.network

import com.squareup.moshi.Json

data class DogPhoto(
    @Json(name = "message") val imageUrl: List<String>?,
    @Json(name = "status") val statusResponse: String?
)