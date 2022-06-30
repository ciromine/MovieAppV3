package com.example.ciromine.movieappv3.data.mapper

import com.example.ciromine.movieappv3.data.remote.model.MovieListResponse
import com.example.ciromine.movieappv3.data.remote.model.MovieResponse
import org.junit.Assert.*
import org.junit.Test

class DataResponseMapperTest {

    private val mapper = DataResponseMapper()

    @Test
    fun `given MovieListResponse when toDomain(), then DomainMovieList`() {
        val remoteMovieListResponse = MovieListResponse(
            listOf(MovieResponse(
                "title",
                "posterPath",
                "backdropPath",
                "originalTitle",
                "overview",
                "releaseDate"))
        )

        val domainMovieList = with(mapper) {
            remoteMovieListResponse.toDomain()
        }

        assertEquals(domainMovieList.results.first().title, remoteMovieListResponse.results.first().title)
        assertEquals(domainMovieList.results.first().backdropPath, remoteMovieListResponse.results.first().backdropPath)
        assertEquals(domainMovieList.results.first().originalTitle, remoteMovieListResponse.results.first().originalTitle)
        assertEquals(domainMovieList.results.first().overview, remoteMovieListResponse.results.first().overview)
        assertEquals(domainMovieList.results.first().posterPath, remoteMovieListResponse.results.first().posterPath)
        assertEquals(domainMovieList.results.first().releaseDate, remoteMovieListResponse.results.first().releaseDate)
    }
}