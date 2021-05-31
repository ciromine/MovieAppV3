package com.example.ciromine.movieappv3.ui.di

import com.example.ciromine.movieappv3.core.execution.CoroutineExecutionThread
import com.example.ciromine.movieappv3.core.execution.ExecutionThread
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CoroutineDispatcherModule {

    @Reusable
    @Provides
    fun provideCoroutineDispatchers(): CoroutineExecutionThread {
        return ExecutionThread()
    }
}