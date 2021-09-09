package com.ekotech.breakingbad.data.repository

import com.ekotech.breakingbad.data.api.ApiService
import com.ekotech.breakingbad.data.domain.BreakingBadCharacters
import com.ekotech.breakingbad.data.local.BreakingBadCharactersLocal
import com.ekotech.breakingbad.data.local.dao.BreakingBadCharactersDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(private val service: ApiService, private val charactersDao: BreakingBadCharactersDao) : CharactersRepository {

    override suspend fun getCharacters(): List<BreakingBadCharacters> {
        return withContext(Dispatchers.IO) {
            val localData = charactersDao.getALlCharacters()
            if (localData.isEmpty()) {
                service.getCharacters().also { characters ->
                    return@also charactersDao.insertAllCharacters(characters.map {
                        BreakingBadCharactersLocal(
                            it.id,
                            it.image,
                            it.name,
                            it.occupation,
                            it.status,
                            it.nickname,
                            it.seasonAppearance
                        )
                    })
                }
            } else {
                return@withContext localData.map {
                    BreakingBadCharacters(
                        it.id,
                        it.image,
                        it.name,
                        it.occupation!!,
                        it.status,
                        it.nickname,
                        it.seasonAppearance!!
                    )
                }
            }
        }
    }
}

