package com.example.ciromine.movieappv3.presentation.movielist

import com.example.ciromine.movieappv3.core.execution.FakeCoroutineExecutionThread
import com.example.ciromine.movieappv3.domain.GetMovieListUseCase
import com.example.ciromine.movieappv3.domain.model.DomainMovie
import com.example.ciromine.movieappv3.domain.model.DomainMovieList
import com.example.ciromine.movieappv3.presentation.movielist.MovieListAction.*
import com.example.ciromine.movieappv3.presentation.movielist.MovieListResult.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class MovieListProcessorTest {
    private val getMovieListUseCase = mockk<GetMovieListUseCase>(relaxed = true)

    private val processor =
        MovieListProcessor(getMovieListUseCase, FakeCoroutineExecutionThread())

    private val error = Throwable()

    private val stubDomainMovie = DomainMovie("","","","","","")

    private val stubDomainMovieList = DomainMovieList(
        results = listOf(stubDomainMovie)
    )

    @Test
    fun `given GetMovieListAction and use case with DomainMovieList, when calls 'actionProcessor', then return GetMovieListResult-Success`() =
        runBlocking {
            stubGetCharacterListUseCase(stubDomainMovieList)

            val results = processor.actionProcessor(GetMovieListAction).toList()

            assertEquals(results[0], GetMovieListResult.InProgress)
            assertEquals(results[1], GetMovieListResult.Success(stubDomainMovieList.results))
        }

    @Test
    fun `given GetMovieListAction and use case with error, when calls 'actionProcessor', then return GetMovieListResult-Error`() = runBlocking {
        stubGetCharacterListUseCaseError()

        val results = processor.actionProcessor(GetMovieListAction).toList()

        assertEquals(results[0], GetMovieListResult.InProgress)
        assertEquals(results[1], GetMovieListResult.Error)
    }

    @Test
    fun `given GoToDetailAction, when calls 'actionProcessor', then return NavigateToResult-GoToDetail`() = runBlocking {
        val results = processor.actionProcessor(
            GoToDetailAction(
                DomainMovie("","","","","","")
            )
        ).toList()

        assertEquals(results[0], NavigateToResult.GoToDetail(
                DomainMovie("","","","","","")
            )
        )
    }

    private fun stubGetCharacterListUseCase(domainMovieList: DomainMovieList) {
        coEvery { getMovieListUseCase.execute() } returns flow { emit(domainMovieList) }
    }

    private fun stubGetCharacterListUseCaseError() {
        coEvery { getMovieListUseCase.execute() } returns flow { throw error }
    }
}