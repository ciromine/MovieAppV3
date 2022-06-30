package com.example.ciromine.movieappv3.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ciromine.movieappv3.core.mvi.MviPresentation
import com.example.ciromine.movieappv3.core.mvi.MviPresentationEffect
import com.example.ciromine.movieappv3.presentation.movielist.MovieListAction.*
import com.example.ciromine.movieappv3.presentation.movielist.MovieListResult.*
import com.example.ciromine.movieappv3.presentation.movielist.MovieListUIntent.*
import com.example.ciromine.movieappv3.presentation.movielist.MovieListUiEffect.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val reducer: MovieListReducer,
    private val actionProcessorHolder: MovieListProcessor
) : ViewModel(), MviPresentation<MovieListUIntent, MovieListUiState>,
    MviPresentationEffect<MovieListUiEffect> {

    private val defaultUiState: MovieListUiState = MovieListUiState.DefaultUiState

    private val uiState = MutableStateFlow(defaultUiState)
    private val uiEffect: MutableSharedFlow<MovieListUiEffect> = MutableSharedFlow()

    override fun processUserIntents(userIntents: Flow<MovieListUIntent>) {
        userIntents
            .buffer()
            .flatMapMerge { userIntent ->
                actionProcessorHolder.actionProcessor(userIntent.toAction())
            }
            .handleEffect()
            .scan(defaultUiState) { previousUiState, result ->
                with(reducer) { previousUiState reduce result }
            }
            .onEach { uiState ->
                this.uiState.value = uiState
            }
            .launchIn(viewModelScope)
    }

    private fun MovieListUIntent.toAction(): MovieListAction {
        return when (this) {
            InitialUIntent, RetrySeeCharacterListUIntent -> GetMovieListAction
            is SeeDetailUIntent -> GoToDetailAction(domainMovie)
        }
    }

    private fun Flow<MovieListResult>.handleEffect(): Flow<MovieListResult> {
        return onEach { change ->
            val event = when (change) {
                is NavigateToResult.GoToDetail -> NavigateToCharacterDetailUiEffect(
                    change.domainMovie
                )
                else -> return@onEach
            }
            uiEffect.emit(event)
        }
    }

    override fun uiStates(): StateFlow<MovieListUiState> = uiState
    override fun uiEffect(): SharedFlow<MovieListUiEffect> = uiEffect.asSharedFlow()
}