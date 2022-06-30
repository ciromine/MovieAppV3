package com.example.ciromine.movieappv3.presentation.movielist

import com.example.ciromine.movieappv3.core.mvi.events.MviAction

sealed class MovieListAction : MviAction {

    object GetMovieListAction : MovieListAction()

    data class GoToDetailAction(val id: Int) : MovieListAction()
}