package com.example.ciromine.movieappv3.core.mvi

import com.example.ciromine.movieappv3.core.mvi.events.MviResult
import com.example.ciromine.movieappv3.core.mvi.events.MviUiState

class UnsupportedReduceException(state: MviUiState, result: MviResult) :
    RuntimeException("Cannot reduce state: $state with result: $result")