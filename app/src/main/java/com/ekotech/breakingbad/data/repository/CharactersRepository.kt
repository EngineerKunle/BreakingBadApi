package com.ekotech.breakingbad.data.repository

import com.ekotech.breakingbad.data.domain.BreakingBadCharacters

interface CharactersRepository {
    suspend fun getCharacters(): List<BreakingBadCharacters>
}