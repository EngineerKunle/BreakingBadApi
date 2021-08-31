package com.ekotech.breakingbad.data.usecase

import com.ekotech.breakingbad.data.repository.CharactersRepository
import javax.inject.Inject

class GetCharacters @Inject constructor(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke() = charactersRepository.getCharacters()
}
