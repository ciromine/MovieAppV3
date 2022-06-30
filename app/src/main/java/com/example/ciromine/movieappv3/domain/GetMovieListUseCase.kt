package com.example.ciromine.movieappv3.domain

import com.example.ciromine.movieappv3.domain.model.DomainMovieList
import com.example.ciromine.movieappv3.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieListUseCase
@Inject
constructor(private val repository: MovieRepository) {
    fun execute(): Flow<DomainMovieList> = repository.getMovieList()
}