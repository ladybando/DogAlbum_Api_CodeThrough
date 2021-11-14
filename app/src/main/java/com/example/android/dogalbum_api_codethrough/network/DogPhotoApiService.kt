package com.example.android.dogalbum_api_codethrough.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://dog.ceo/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DogPhotoApiService {
    //https://dog.ceo/api/breeds/image/random
    @GET("breeds/image/random/48")
    suspend fun getRandomPhoto(): DogPhoto

    @GET("breed/{breed}/images/random")
    suspend fun getPhotoByBreed(@Path("breed") breed: String): DogPhoto
}

object DogPhotoApi {
    val retrofitService: DogPhotoApiService by lazy {
        retrofit.create((DogPhotoApiService::class.java))
    }
}