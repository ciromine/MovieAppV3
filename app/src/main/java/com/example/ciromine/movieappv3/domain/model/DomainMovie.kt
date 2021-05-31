package com.example.ciromine.movieappv3.domain.model

data class DomainMovie(
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val originalTitle: String,
    val overview: String,
    val releaseDate: String
)