package com.example.ciromine.movieappv3.data.source

import com.example.ciromine.movieappv3.data.remote.model.MovieListResponse

interface MovieRemote {

    suspend fun getMovieList(): MovieListResponse
}