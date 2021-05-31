package com.example.ciromine.movieappv3.presentation.movielist

import com.example.ciromine.movieappv3.core.mvi.events.MviUserIntent

sealed class MovieListUIntent : MviUserIntent {

    object InitialUIntent : MovieListUIntent()

    object RetrySeeCharacterListUIntent : MovieListUIntent()

    data class SeeDetailUIntent(val id: Int) : MovieListUIntent()
}