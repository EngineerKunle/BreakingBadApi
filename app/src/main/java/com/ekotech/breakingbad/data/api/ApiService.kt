package com.ekotech.breakingbad.data.api

import com.ekotech.breakingbad.data.domain.BreakingBadCharacters
import retrofit2.http.GET

interface ApiService {
    @GET("api/characters")
    suspend fun getCharacters(): List<BreakingBadCharacters>
}
