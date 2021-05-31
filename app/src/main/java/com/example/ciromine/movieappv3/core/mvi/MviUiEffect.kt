package com.example.ciromine.movieappv3.core.mvi

import com.example.ciromine.movieappv3.core.mvi.events.MviEffect

interface MviUiEffect<in TUiEffect : MviEffect> {
    fun handleEffect(uiEffect: TUiEffect)
}