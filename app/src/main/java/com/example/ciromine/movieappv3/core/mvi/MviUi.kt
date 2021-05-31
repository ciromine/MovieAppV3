package com.example.ciromine.movieappv3.core.mvi

import com.example.ciromine.movieappv3.core.mvi.events.MviUiState
import com.example.ciromine.movieappv3.core.mvi.events.MviUserIntent
import kotlinx.coroutines.flow.Flow

interface MviUi<TUserIntent: MviUserIntent, in TUiState: MviUiState> {

    fun userIntents(): Flow<TUserIntent>

    fun renderUiStates(uiState: TUiState)
}