package com.ekotech.breakingbad.viewstate

sealed class CharactersViewState {
    object LoadingState : CharactersViewState()
    data class Success(val data: List<CharactersModel>) : CharactersViewState()
    data class Error(val error: String = "Loading fail") : CharactersViewState()
}
