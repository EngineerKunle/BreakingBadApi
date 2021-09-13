package com.ekotech.breakingbad.data.domain

import com.ekotech.breakingbad.data.local.BreakingBadCharactersLocal
import com.google.gson.annotations.SerializedName

data class BreakingBadCharacters(
    @SerializedName("char_id")
    val id: Int,
    @SerializedName("img")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("occupation")
    val occupation: List<String>,
    @SerializedName("status")
    val status: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("appearance")
    val seasonAppearance: List<Int>
)

fun BreakingBadCharacters.toLocal() = BreakingBadCharactersLocal(
    this.id,
    this.image,
    this.name,
    this.occupation,
    this.status,
    this.nickname,
    this.seasonAppearance
)
