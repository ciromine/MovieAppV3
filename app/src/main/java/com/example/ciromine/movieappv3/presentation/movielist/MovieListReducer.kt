package com.example.ciromine.movieappv3.presentation.movielist

import com.example.ciromine.movieappv3.core.mvi.MviReducer
import com.example.ciromine.movieappv3.core.mvi.UnsupportedReduceException
import com.example.ciromine.movieappv3.presentation.movielist.MovieListResult.*
import com.example.ciromine.movieappv3.presentation.movielist.MovieListResult.GetMovieListResult.*
import com.example.ciromine.movieappv3.presentation.movielist.MovieListUiState.*
import javax.inject.Inject
import kotlin.Error

class MovieListReducer @Inject constructor() :
    MviReducer<MovieListUiState, MovieListResult> {

    override fun MovieListUiState.reduce(result: MovieListResult): MovieListUiState {
        return when (val previousState = this) {
            is DefaultUiState -> previousState reduce result
            is LoadingUiState -> previousState reduce result
            is SuccessUiState -> previousState reduce result
            is ErrorUiState -> previousState reduce result
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun DefaultUiState.reduce(result: MovieListResult): MovieListUiState {
        return when (result) {
            InProgress -> LoadingUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun LoadingUiState.reduce(result: MovieListResult): MovieListUiState {
        return when (result) {
            is Success -> SuccessUiState(result.results)
            is Error -> ErrorUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun SuccessUiState.reduce(result: MovieListResult): MovieListUiState {
        return when (result) {
            is NavigateToResult.GoToDetail -> this
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun ErrorUiState.reduce(result: MovieListResult): MovieListUiState {
        return when (result) {
            InProgress -> LoadingUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }
}