package com.example.android.dogalbum_api_codethrough.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://dog.ceo/api/"
 /*
  * Retrofit takes base URI for web services
  * and converter factory to build webs services api.
  * Retrofit  fetches JSON response and MoshiConverter
  * converts response to string */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
    
/**
 * A public interface that exposes the [getRandomPhoto] method
 */
interface DogPhotoApiService {
        /**
         *
         * The @GET annotation indicates that the "breeds/image/random" endpoint will be requested with the GET
         * HTTP method
         */
    //https://dog.ceo/api/breeds/image/random
    @GET("breeds/image/random")
    suspend fun getRandomPhoto(): DogPhoto

}

object DogPhotoApi {
    /**
      * A public Api object that exposes the lazy-initialized Retrofit service
      */
    val retrofitService: DogPhotoApiService by lazy {
        retrofit.create((DogPhotoApiService::class.java))
    }
}
