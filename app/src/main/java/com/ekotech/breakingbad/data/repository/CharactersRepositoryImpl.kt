package com.ekotech.breakingbad.data.repository

import com.ekotech.breakingbad.data.api.ApiService
import com.ekotech.breakingbad.data.domain.BreakingBadCharacters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(private val service: ApiService) : CharactersRepository {
    override suspend fun getCharacters(): List<BreakingBadCharacters> = withContext(Dispatchers.IO) { service.getCharacters() }
}
