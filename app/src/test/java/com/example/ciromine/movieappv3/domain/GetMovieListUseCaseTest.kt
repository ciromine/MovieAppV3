package com.example.ciromine.movieappv3.domain

import com.example.ciromine.movieappv3.data.MovieDataRepository
import com.example.ciromine.movieappv3.domain.model.DomainMovie
import com.example.ciromine.movieappv3.domain.model.DomainMovieList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class GetMovieListUseCaseTest {
    private val repository = mockk<MovieDataRepository>()

    private val useCase = GetMovieListUseCase(repository)

    private val domainMovieList = DomainMovieList(
        results = listOf(DomainMovie("","","","","",""))
    )

    @OptIn(InternalCoroutinesApi::class)
    @Test
    fun `when calls 'execute', then return DomainMovieList`() = runBlocking {
        stubRepository(domainMovieList)

        val flow = useCase.execute()

        flow.collect { result ->
            assertEquals(domainMovieList, result)
        }

        coVerify { repository.getMovieList() }

    }

    private fun stubRepository(domainCharacterList: DomainMovieList) {
        coEvery { repository.getMovieList() } returns flow { emit(domainCharacterList) }
    }
}