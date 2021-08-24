package com.ekotech.breakingbad.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ekotech.breakingbad.data.domain.BreakingBadCharacters
import com.ekotech.breakingbad.data.repository.CharactersRepository
import com.ekotech.breakingbad.viewmodel.utils.getOrAwaitValue
import com.ekotech.breakingbad.viewstate.SeasonFilter
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class CharactersViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: CharactersViewModel
    private lateinit var charactersRepository: CharactersRepository

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        charactersRepository = mockk(relaxed = true)
        coEvery { charactersRepository.getCharacters() } returns characters()
        viewModel = CharactersViewModel(charactersRepository)
    }

    @Test
    fun `given list of character when lists is filtered by query then display new list`() {
        val result = viewModel.filterCharacters("W")
        assertEquals(result.getOrAwaitValue().size, 1)
    }

    @Test
    fun `given list of character when lists filter by season two display new list`() {
        val result = viewModel.filterBySeason(SeasonFilter.SEASON_2)
        assertEquals(result.getOrAwaitValue()[0].name, "Hank")
        assertEquals(result.getOrAwaitValue().size, 1)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun characters(): List<BreakingBadCharacters> = listOf(
        BreakingBadCharacters(1, "walker.png", "Walter", occupationsWalt(), "Alive", "King", seasonsWalt()),
        BreakingBadCharacters(2, "hank.png", "Hank", occupationsHank(), "Dead", "Police", seasonsHank()),
    )

    private fun occupationsWalt(): List<String> = listOf(
        "Dealer",
        "Shop Owner",
        "Chemist"
    )

    private fun occupationsHank(): List<String> = listOf(
        "Police",
        "Husband"
    )

    private fun seasonsWalt(): List<Int> = listOf(1, 3, 4, 5)

    private fun seasonsHank(): List<Int> = listOf(2, 3, 4, 5)
}

