package com.youssefmabrouk.heritagequest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youssefmabrouk.heritagequest.data.AnswerResult
import com.youssefmabrouk.heritagequest.data.CategoryId
import com.youssefmabrouk.heritagequest.data.Difficulty
import com.youssefmabrouk.heritagequest.data.HeritageRepository
import com.youssefmabrouk.heritagequest.data.QuizCategory
import com.youssefmabrouk.heritagequest.data.QuizQuestion
import com.youssefmabrouk.heritagequest.data.QuizScorer
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class QuizUiState(
    val categories: List<QuizCategory> = emptyList(),
    val selectedCategoryId: CategoryId = CategoryId.ModernHeritage,
    val selectedDifficulty: Difficulty = Difficulty.Medium,
    val questions: List<QuizQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val selectedOption: String? = null,
    val answerResult: AnswerResult? = null,
    val score: Int = 0,
    val timerEnabled: Boolean = true,
    val soundEnabled: Boolean = true,
    val hapticsEnabled: Boolean = true,
    val timeLeft: Int = Difficulty.Medium.secondsPerQuestion,
    val lifecycleStage: String = "created"
) {
    val currentQuestion: QuizQuestion?
        get() = questions.getOrNull(currentIndex)

    val isLastQuestion: Boolean
        get() = questions.isNotEmpty() && currentIndex == questions.lastIndex

    val completedQuestions: Int
        get() = currentIndex + if (answerResult == null) 0 else 1

    val percentage: Int
        get() = QuizScorer.percentage(score, questions.size)
}

class QuizViewModel(
    private val repository: HeritageRepository = HeritageRepository()
) : ViewModel() {
    // The screen reads this state and updates when it changes.
    private val _uiState = MutableStateFlow(
        QuizUiState(categories = repository.categories)
    )
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null

    fun recordLifecycle(stage: String) {
        _uiState.update { it.copy(lifecycleStage = stage) }
    }

    fun selectCategory(categoryId: CategoryId) {
        _uiState.update { it.copy(selectedCategoryId = categoryId) }
    }

    fun selectDifficulty(difficulty: Difficulty) {
        _uiState.update {
            it.copy(
                selectedDifficulty = difficulty,
                timeLeft = difficulty.secondsPerQuestion
            )
        }
    }

    fun toggleTimer() {
        _uiState.update { it.copy(timerEnabled = !it.timerEnabled) }
    }

    fun toggleSound() {
        _uiState.update { it.copy(soundEnabled = !it.soundEnabled) }
    }

    fun toggleHaptics() {
        _uiState.update { it.copy(hapticsEnabled = !it.hapticsEnabled) }
    }

    fun startQuiz() {
        timerJob?.cancel()
        _uiState.update {
            // Questions and answers are shuffled so the quiz is not always the same.
            val questions = repository
                .questionsFor(it.selectedCategoryId, it.selectedDifficulty)
                .shuffled()
                .map { question -> question.copy(options = question.options.shuffled()) }
            it.copy(
                questions = questions,
                currentIndex = 0,
                selectedOption = null,
                answerResult = null,
                score = 0,
                timeLeft = it.selectedDifficulty.secondsPerQuestion
            )
        }
        startTimer()
    }

    fun submitAnswer(option: String) {
        val state = _uiState.value
        val question = state.currentQuestion ?: return
        if (state.answerResult != null) return

        // Stop the timer after the player answers.
        timerJob?.cancel()
        val isCorrect = option == question.answer
        _uiState.update {
            it.copy(
                selectedOption = option,
                answerResult = AnswerResult(
                    isCorrect = isCorrect,
                    correctAnswer = question.answer,
                    explanation = question.keyFact
                ),
                score = QuizScorer.score(it.score, isCorrect)
            )
        }
    }

    fun nextQuestion() {
        val state = _uiState.value
        if (state.isLastQuestion) return

        timerJob?.cancel()
        _uiState.update {
            it.copy(
                currentIndex = it.currentIndex + 1,
                selectedOption = null,
                answerResult = null,
                timeLeft = it.selectedDifficulty.secondsPerQuestion
            )
        }
        startTimer()
    }

    fun restart() {
        startQuiz()
    }

    private fun startTimer() {
        val state = _uiState.value
        if (!state.timerEnabled || state.questions.isEmpty()) return

        // The timer counts down every second for the current question.
        timerJob = viewModelScope.launch {
            while (_uiState.value.timeLeft > 0 && _uiState.value.answerResult == null) {
                delay(1000)
                _uiState.update { it.copy(timeLeft = (it.timeLeft - 1).coerceAtLeast(0)) }
            }

            val latest = _uiState.value
            val question = latest.currentQuestion
            if (latest.timeLeft == 0 && question != null && latest.answerResult == null) {
                _uiState.update {
                    it.copy(
                        answerResult = AnswerResult(
                            isCorrect = false,
                            correctAnswer = question.answer,
                            explanation = "Time is up. ${question.keyFact}"
                        )
                    )
                }
            }
        }
    }

    override fun onCleared() {
        timerJob?.cancel()
        super.onCleared()
    }
}
