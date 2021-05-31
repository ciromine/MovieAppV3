package com.example.ciromine.movieappv3.ui.di

import com.example.ciromine.movieappv3.data.remote.MovieApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Reusable
    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }
}