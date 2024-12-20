package com.example.ciromine.movieappv3.presentation.movielist

import com.example.ciromine.movieappv3.core.mvi.events.MviAction
import com.example.ciromine.movieappv3.domain.model.DomainMovie

sealed class MovieListAction : MviAction {

    object GetMovieListAction : MovieListAction()

    data class GoToDetailAction(val domainMovie: DomainMovie) : MovieListAction()
}