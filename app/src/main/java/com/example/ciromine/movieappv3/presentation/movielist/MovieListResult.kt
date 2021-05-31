package com.example.ciromine.movieappv3.presentation.movielist

import com.example.ciromine.movieappv3.core.mvi.events.MviResult
import com.example.ciromine.movieappv3.domain.model.DomainMovie

sealed class MovieListResult : MviResult {

    sealed class GetResult : MovieListResult() {
        object InProgress : MovieListResult()
        data class Success(val results: List<DomainMovie>) : MovieListResult()
        object Error : MovieListResult()
    }

    sealed class NavigateToResult : MovieListResult() {
        data class GoToDetail(val id: Int) : NavigateToResult()
    }
}