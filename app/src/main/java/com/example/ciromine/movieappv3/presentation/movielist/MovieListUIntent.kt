package com.example.ciromine.movieappv3.presentation.movielist

import com.example.ciromine.movieappv3.core.mvi.events.MviUserIntent
import com.example.ciromine.movieappv3.domain.model.DomainMovie

sealed class MovieListUIntent : MviUserIntent {

    object InitialUIntent : MovieListUIntent()

    object RetrySeeCharacterListUIntent : MovieListUIntent()

    data class SeeDetailUIntent(val domainMovie: DomainMovie) : MovieListUIntent()
}