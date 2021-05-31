package com.example.ciromine.movieappv3.data.remote

import com.example.ciromine.movieappv3.data.remote.model.MovieListResponse
import com.example.ciromine.movieappv3.utils.Constants
import retrofit2.http.GET

interface MovieApi {

    @GET(Constants.getMovies + "?api_key=${Constants.apiKey}")
    suspend fun getMovies(): MovieListResponse
}