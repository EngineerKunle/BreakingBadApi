package com.ekotech.breakingbad.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ekotech.breakingbad.data.domain.BreakingBadCharacters

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

fun BreakingBadCharactersLocal.toDomain() = BreakingBadCharacters(
    this.id,
    this.image,
    this.name,
    this.occupation ?: emptyList(),
    this.status,
    this.nickname,
    this.seasonAppearance ?: emptyList()
)
