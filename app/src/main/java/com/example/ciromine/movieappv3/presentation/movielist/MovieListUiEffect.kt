package com.example.ciromine.movieappv3.presentation.movielist

import com.example.ciromine.movieappv3.core.mvi.events.MviEffect
import com.example.ciromine.movieappv3.domain.model.DomainMovie

sealed class MovieListUiEffect : MviEffect {
    data class NavigateToCharacterDetailUiEffect(val domainMovie: DomainMovie) : MovieListUiEffect()
}