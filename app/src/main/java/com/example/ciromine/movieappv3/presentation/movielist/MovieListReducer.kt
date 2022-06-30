package com.example.ciromine.movieappv3.presentation.movielist

import com.example.ciromine.movieappv3.core.mvi.MviReducer
import com.example.ciromine.movieappv3.core.mvi.UnsupportedReduceException
import javax.inject.Inject

class MovieListReducer @Inject constructor() :
    MviReducer<MovieListUiState, MovieListResult> {

    override fun MovieListUiState.reduce(result: MovieListResult): MovieListUiState {
        return when (val previousState = this) {
            is MovieListUiState.DefaultUiState -> previousState reduce result
            is MovieListUiState.LoadingUiState -> previousState reduce result
            is MovieListUiState.SuccessUiState -> previousState reduce result
            is MovieListUiState.ErrorUiState -> previousState reduce result
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun MovieListUiState.DefaultUiState.reduce(result: MovieListResult): MovieListUiState {
        return when (result) {
            MovieListResult.GetMovieListResult.InProgress -> MovieListUiState.LoadingUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun MovieListUiState.LoadingUiState.reduce(result: MovieListResult): MovieListUiState {
        return when (result) {
            is MovieListResult.GetMovieListResult.Success -> MovieListUiState.SuccessUiState(result.results)
            is Error -> MovieListUiState.ErrorUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun MovieListUiState.SuccessUiState.reduce(result: MovieListResult): MovieListUiState {
        return when (result) {
            is MovieListResult.NavigateToResult.GoToDetail -> this
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun MovieListUiState.ErrorUiState.reduce(result: MovieListResult): MovieListUiState {
        return when (result) {
            MovieListResult.GetMovieListResult.InProgress -> MovieListUiState.LoadingUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }
}