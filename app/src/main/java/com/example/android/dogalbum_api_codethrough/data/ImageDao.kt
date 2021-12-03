package com.example.android.dogalbum_api_codethrough.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {
    @Query("SELECT * FROM images")
    fun getAllImages(): Flow<List<ImageEntity>>

    @Query("SELECT * FROM images ORDER BY id DESC LIMIT 1")
    fun getNewImage() : ImageEntity

    @Query("DELETE from images where id=(select max(id)-1 from images)")
    suspend fun deleteImage()

    @Insert
    suspend fun insertImage(dogImageEntity: ImageEntity)
}