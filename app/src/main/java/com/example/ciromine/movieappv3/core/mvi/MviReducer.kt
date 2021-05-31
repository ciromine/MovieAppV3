package com.example.ciromine.movieappv3.core.mvi

import com.example.ciromine.movieappv3.core.mvi.events.MviResult
import com.example.ciromine.movieappv3.core.mvi.events.MviUiState

interface MviReducer<TUiState : MviUiState, TResult: MviResult> {

    infix fun TUiState.reduce(result: TResult): TUiState
}