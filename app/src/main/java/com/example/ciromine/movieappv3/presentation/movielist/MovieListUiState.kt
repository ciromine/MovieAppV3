package com.example.ciromine.movieappv3.presentation.movielist

import com.example.ciromine.movieappv3.core.mvi.events.MviUiState
import com.example.ciromine.movieappv3.domain.model.DomainMovie

sealed class MovieListUiState : MviUiState {
    object DefaultUiState : MovieListUiState()
    object LoadingUiState : MovieListUiState()
    data class SuccessUiState(val characters: List<DomainMovie>) : MovieListUiState()
    object ErrorUiState : MovieListUiState()
}