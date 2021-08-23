package com.ekotech.breakingbad.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekotech.breakingbad.data.domain.BreakingBadCharacters
import com.ekotech.breakingbad.data.repository.CharactersRepository
import com.ekotech.breakingbad.viewstate.SeasonFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val _charactersData = MutableLiveData<MutableList<BreakingBadCharacters>>()
    val charactersData = _charactersData

    private var _filterCharactersData = MutableLiveData<MutableList<BreakingBadCharacters>>()

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            val charactersGET = charactersRepository.getCharacters().toMutableList()
            _charactersData.postValue(charactersGET)
        }
    }

    fun filterCharacters(query: String): LiveData<MutableList<BreakingBadCharacters>> {
        return Transformations.switchMap(_charactersData) { characters ->
            _filterCharactersData = MutableLiveData()
            val filteredList = characters.filter {
                it.name.contains(query, ignoreCase = true)
            }
            _filterCharactersData.postValue(filteredList.toMutableList())
            _filterCharactersData
        }
    }

    fun filterBySeason(season: SeasonFilter): LiveData<MutableList<BreakingBadCharacters>> {
        return Transformations.switchMap(_charactersData) { characters ->
            _filterCharactersData = MutableLiveData()
            val filteredList = characters.filter {
                it.seasonAppearance.contains(season.number)
            }
            _filterCharactersData.postValue(filteredList.toMutableList())
            _filterCharactersData
        }
    }
}
