package com.youssefmabrouk.heritagequest

import com.youssefmabrouk.heritagequest.data.CategoryId
import com.youssefmabrouk.heritagequest.data.Difficulty
import com.youssefmabrouk.heritagequest.viewmodel.QuizViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class QuizViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun startQuizLoadsQuestions() {
        val viewModel = QuizViewModel()

        viewModel.selectCategory(CategoryId.NaturalMixed)
        viewModel.selectDifficulty(Difficulty.Hard)
        viewModel.toggleTimer()
        viewModel.startQuiz()

        val state = viewModel.uiState.value
        assertEquals(CategoryId.NaturalMixed, state.selectedCategoryId)
        assertEquals(Difficulty.Hard, state.selectedDifficulty)
        assertEquals(6, state.questions.size)
        assertFalse(state.timerEnabled)
    }

    @Test
    fun correctAnswerUpdatesScore() {
        val viewModel = QuizViewModel()
        viewModel.toggleTimer()
        viewModel.startQuiz()

        val answer = viewModel.uiState.value.currentQuestion!!.answer
        viewModel.submitAnswer(answer)

        val state = viewModel.uiState.value
        assertEquals(10, state.score)
        assertNotNull(state.answerResult)
        assertTrue(state.answerResult!!.isCorrect)
    }
}
