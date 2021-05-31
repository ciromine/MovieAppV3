package com.example.ciromine.movieappv3.core.mvi

import com.example.ciromine.movieappv3.core.mvi.events.MviUiState
import com.example.ciromine.movieappv3.core.mvi.events.MviUserIntent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MviPresentation<TUserIntent: MviUserIntent, TUiState: MviUiState> {

    fun processUserIntents(userIntents: Flow<TUserIntent>)

    fun uiStates(): StateFlow<TUiState>
}