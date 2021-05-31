package com.example.ciromine.movieappv3.presentation.movielist

import com.example.ciromine.movieappv3.core.execution.CoroutineExecutionThread
import com.example.ciromine.movieappv3.domain.GetMovieListUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieListProcessor @Inject constructor(
    private val useCase: GetMovieListUseCase,
    private val coroutineThreadProvider: CoroutineExecutionThread
) {

    fun actionProcessor(actions: MovieListAction): Flow<MovieListResult> =
        when (actions) {
            is MovieListAction.GetMainAction -> getMovieListActionProcessor()
            is MovieListAction.GoToDetailAction -> goToDetailActionProcessor(actions.id)
        }

    private fun getMovieListActionProcessor(): Flow<MovieListResult> =
        useCase.execute()
            .map {
                MovieListResult.GetResult.Success(it.results) as MovieListResult
            }.onStart {
                emit(MovieListResult.GetResult.InProgress)
            }.catch {
                emit(MovieListResult.GetResult.Error)
            }
            .flowOn(coroutineThreadProvider.ioThread())

    private fun goToDetailActionProcessor(id: Int): Flow<MovieListResult> = flow {
        emit(MovieListResult.NavigateToResult.GoToDetail(id))
    }
}