package com.example.ciromine.movieappv3.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DomainMovie(
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val originalTitle: String,
    val overview: String,
    val releaseDate: String
): Parcelable