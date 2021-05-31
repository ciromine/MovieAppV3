package com.example.ciromine.movieappv3.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ciromine.movieappv3.core.mvi.MviPresentation
import com.example.ciromine.movieappv3.core.mvi.MviPresentationEffect
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
            MovieListUIntent.InitialUIntent, MovieListUIntent.RetrySeeCharacterListUIntent -> MovieListAction.GetMainAction
            is MovieListUIntent.SeeDetailUIntent -> MovieListAction.GoToDetailAction(id)
        }
    }

    private fun Flow<MovieListResult>.handleEffect(): Flow<MovieListResult> {
        return onEach { change ->
            val event = when (change) {
                is MovieListResult.NavigateToResult.GoToDetail -> MovieListUiEffect.NavigateToCharacterDetailUiEffect(
                    change.id
                )
                else -> return@onEach
            }
            uiEffect.emit(event)
        }
    }

    override fun uiStates(): StateFlow<MovieListUiState> = uiState
    override fun uiEffect(): SharedFlow<MovieListUiEffect> = uiEffect.asSharedFlow()
}