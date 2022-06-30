package com.example.ciromine.movieappv3.presentation.movielist

import com.example.ciromine.movieappv3.core.execution.CoroutineExecutionThread
import com.example.ciromine.movieappv3.domain.GetMovieListUseCase
import com.example.ciromine.movieappv3.domain.model.DomainMovie
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieListProcessor @Inject constructor(
    private val useCase: GetMovieListUseCase,
    private val coroutineThreadProvider: CoroutineExecutionThread
) {

    fun actionProcessor(actions: MovieListAction): Flow<MovieListResult> =
        when (actions) {
            is MovieListAction.GetMovieListAction -> getMovieListActionProcessor()
            is MovieListAction.GoToDetailAction -> goToDetailActionProcessor(actions.domainMovie)
        }

    private fun getMovieListActionProcessor(): Flow<MovieListResult> =
        useCase.execute()
            .map {
                MovieListResult.GetMovieListResult.Success(it.results) as MovieListResult
            }.onStart {
                emit(MovieListResult.GetMovieListResult.InProgress)
            }.catch {
                emit(MovieListResult.GetMovieListResult.Error)
            }
            .flowOn(coroutineThreadProvider.ioThread())

    private fun goToDetailActionProcessor(domainMovie: DomainMovie): Flow<MovieListResult> = flow {
        emit(MovieListResult.NavigateToResult.GoToDetail(domainMovie))
    }
}