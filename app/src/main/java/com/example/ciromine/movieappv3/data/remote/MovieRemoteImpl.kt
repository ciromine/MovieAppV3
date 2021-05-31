package com.example.ciromine.movieappv3.data.remote

import com.example.ciromine.movieappv3.data.remote.model.MovieListResponse
import com.example.ciromine.movieappv3.data.source.MovieRemote
import javax.inject.Inject

class MovieRemoteImpl @Inject constructor(private val restApi: MovieApi) :
    MovieRemote {

    override suspend fun getMovieList(): MovieListResponse = restApi.getMovies()
}