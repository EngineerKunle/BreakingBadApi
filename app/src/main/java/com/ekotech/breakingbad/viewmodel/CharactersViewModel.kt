package com.ekotech.breakingbad.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekotech.breakingbad.data.usecase.GetCharacters
import com.ekotech.breakingbad.viewstate.CharactersModel
import com.ekotech.breakingbad.viewstate.CharactersViewState
import com.ekotech.breakingbad.viewstate.SeasonFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharacters: GetCharacters
) : ViewModel() {

    private val _charactersViewState = MutableLiveData<CharactersViewState>(CharactersViewState.LoadingState)
    val charactersViewState = _charactersViewState

    private var _filterCharactersData = MutableLiveData<List<CharactersModel>>(emptyList())

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            try {
                getCharacters().let {
                    _charactersViewState.value = CharactersViewState.Success(it.map { breakingBadCharacters ->
                        CharactersModel(
                            breakingBadCharacters.id,
                            breakingBadCharacters.name,
                            breakingBadCharacters.image,
                            breakingBadCharacters.occupation,
                            breakingBadCharacters.status,
                            breakingBadCharacters.nickname,
                            breakingBadCharacters.seasonAppearance
                        )
                    })

                    _filterCharactersData.value = (_charactersViewState.value as CharactersViewState.Success).data
                }
            } catch (e: Exception) {
                _charactersViewState.value = CharactersViewState.Error()
            }
        }
    }

    fun filterCharacters(query: String): LiveData<MutableList<CharactersModel>> {
        return Transformations.switchMap(_filterCharactersData) { characters ->
            val data = MutableLiveData<MutableList<CharactersModel>>()
            val list = characters.filter {
                it.name.contains(query, ignoreCase = true)
            }.toMutableList()
            data.postValue(list)
            data
        }
    }

    fun filterBySeason(season: SeasonFilter): LiveData<MutableList<CharactersModel>> {
        return Transformations.switchMap(_filterCharactersData) { characters ->
            val data = MutableLiveData<MutableList<CharactersModel>>()
            val list = characters.filter {
                it.seasonAppearance.contains(season.number)
            }.toMutableList()
            data.postValue(list)
            data
        }
    }
}
