package com.example.ciromine.movieappv3.core.execution

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineExecutionThread {

    fun uiThread(): CoroutineDispatcher

    fun ioThread(): CoroutineDispatcher
}