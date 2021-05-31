package com.example.ciromine.movieappv3.presentation.movielist

import com.example.ciromine.movieappv3.core.mvi.events.MviEffect

sealed class MovieListUiEffect : MviEffect {
    data class NavigateToCharacterDetailUiEffect(val id: Int) : MovieListUiEffect()
}