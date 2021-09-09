package com.ekotech.breakingbad.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Characters")
data class BreakingBadCharactersLocal(
    @PrimaryKey val id: Int,
    val image: String,
    val name: String,
    val occupation: List<String>?,
    val status: String,
    val nickname: String,
    val seasonAppearance: List<Int>?
)