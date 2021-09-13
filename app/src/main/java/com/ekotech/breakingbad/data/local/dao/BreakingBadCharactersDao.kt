package com.ekotech.breakingbad.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ekotech.breakingbad.data.local.BreakingBadCharactersLocal

@Dao
interface BreakingBadCharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<BreakingBadCharactersLocal>)

    @Query("SELECT * FROM Characters")
    suspend fun getALlCharacters(): List<BreakingBadCharactersLocal>

    @Delete
    suspend fun deleteAllCharacters(characters: BreakingBadCharactersLocal)
}
