package com.example.warescatalogue.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.warescatalogue.entity.Ware

@Dao
interface WareDao {

    @Insert
    suspend fun addWare(ware: Ware)

    @Query("SELECT * FROM ware ORDER BY id DESC")
    suspend fun getAllWares(): List<Ware>

    @Update
    suspend fun updateWare(ware: Ware)

    @Delete
    suspend fun deleteWare(ware: Ware)
}