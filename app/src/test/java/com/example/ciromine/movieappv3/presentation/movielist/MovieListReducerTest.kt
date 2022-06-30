package com.example.ciromine.movieappv3.presentation.movielist

import com.example.ciromine.movieappv3.domain.model.DomainMovie
import com.example.ciromine.movieappv3.presentation.movielist.MovieListResult.*
import com.example.ciromine.movieappv3.presentation.movielist.MovieListUiState.*
import org.junit.Test

class MovieListReducerTest {
    private val sutReducer = MovieListReducer()

    @Test
    fun `given DefaultUiState with GetCharacterListResult-InProgress, when reduce, then return LoadingUiState`() {
        val previousState = DefaultUiState
        val result = GetMovieListResult.InProgress

        val newState = with(sutReducer) { previousState reduce result }

        assert(newState is LoadingUiState)
    }

    @Test
    fun `given LoadingUiState with GetCharacterListResult-Success, when reduce, then return SuccessUiState`() {
        val previousState = LoadingUiState
        val result = GetMovieListResult.Success(listOf())

        val newState = with(sutReducer) { previousState reduce result }

        assert(newState is SuccessUiState)
    }

    @Test
    fun `given SuccessUiState with NavigateToResult-GoToDetail, when reduce, then return SuccessUiState`() {
        val previousState = SuccessUiState(listOf())
        val result = NavigateToResult.GoToDetail(
            DomainMovie("","","","","","")
        )

        val newState = with(sutReducer) { previousState reduce result }

        assert(newState is SuccessUiState)
    }

    @Test
    fun `given ErrorUiState with GetCharacterListResult-InProgress, when reduce, then return LoadingUiState`() {
        val previousState = ErrorUiState
        val result = GetMovieListResult.InProgress

        val newState = with(sutReducer) { previousState reduce result }

        assert(newState is LoadingUiState)
    }
}