package com.ekotech.breakingbad.viewstate

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharactersModel(
    val id: Int,
    val name: String,
    val imageURl: String,
    val occupation: List<String>,
    val status: String,
    val nickName: String,
    val seasonAppearance: List<Int>
) : Parcelable
