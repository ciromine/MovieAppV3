package com.example.ciromine.movieappv3.data

import com.example.ciromine.movieappv3.data.mapper.DataResponseMapper
import com.example.ciromine.movieappv3.data.source.MovieRemote
import com.example.ciromine.movieappv3.domain.model.DomainMovieList
import com.example.ciromine.movieappv3.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDataRepository @Inject constructor(
    private val remote: MovieRemote,
    private val mapper: DataResponseMapper
) : MovieRepository {

    override fun getMovieList(): Flow<DomainMovieList> = flow {
        val movieList = with(mapper) {
            remote.getMovieList().toDomain()
        }
        emit(movieList)
    }
}