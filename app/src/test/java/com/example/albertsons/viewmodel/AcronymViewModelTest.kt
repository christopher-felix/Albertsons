package com.example.albertsons.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.albertsons.model.AcronymRepo
import com.example.albertsons.presentation.view.util.AcronymState
import com.example.albertsons.presentation.viewmodel.AcronymViewModel
import com.example.albertsons.testingUtil.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
class AcronymViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule: TestRule = MainDispatcherRule()

    private lateinit var acronymViewModel: AcronymViewModel

    @Before
    fun setUp() {
        mockkObject(AcronymRepo)
        acronymViewModel = AcronymViewModel(AcronymRepo)
    }

    @Test
    fun `Given searchAcronym called, When success call, Then state updated with new list`() =
        runTest {
            // Given
            val shortForm = "sfp"

            val stateObserver = mockk<Observer<AcronymState>>()
            val stateCache = mutableListOf<AcronymState>()

            val response = listOf("asdfsdf", "kljlkjkl")

            coEvery { AcronymRepo.searchForLongForms(shortForm) } coAnswers { response }
            coEvery { stateObserver.onChanged(capture(stateCache)) } answers { }

            // When
            acronymViewModel.uiState.observeForever(stateObserver)
            acronymViewModel.searchAcronym(shortForm)


            // Then
            val lastState = stateCache.last()
            Assert.assertTrue(lastState.meanings.isNotEmpty())
            Assert.assertFalse(lastState.loading)
            Assert.assertEquals("", lastState.error)
        }
}
