package com.example.ciromine.movieappv3.domain.repository

import com.example.ciromine.movieappv3.domain.model.DomainMovieList
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieList(): Flow<DomainMovieList>
}