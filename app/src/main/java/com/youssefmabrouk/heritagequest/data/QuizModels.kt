package com.youssefmabrouk.heritagequest.data

import androidx.annotation.DrawableRes

enum class CategoryId {
    RomanHeritage,
    IslamicHeritage,
    PunicPreRoman,
    ModernHeritage,
    NaturalMixed,
    Cities
}

enum class Difficulty(val title: String, val description: String, val secondsPerQuestion: Int) {
    Easy("Easy", "Recognizable landmarks with generous time.", 20),
    Medium("Medium", "Balanced questions and standard timing.", 15),
    Hard("Hard", "Less time, no second guessing.", 10)
}

enum class IllustrationKind {
    Mausoleum,
    CultureCity,
    Stadium,
    BrutalistHotel,
    Theatre,
    Library,
    Wetland,
    Mountain,
    Savanna,
    Island,
    Gulf,
    Oasis
}

data class QuizCategory(
    val id: CategoryId,
    val title: String,
    val subtitle: String,
    val questionCount: Int,
    val isPlayable: Boolean,
    @DrawableRes val imageResId: Int
)

data class QuizQuestion(
    val id: String,
    val categoryId: CategoryId,
    val difficulty: Difficulty,
    val prompt: String,
    val answer: String,
    val options: List<String>,
    val cityOrLocation: String,
    val keyFact: String,
    val illustrationKind: IllustrationKind,
    @DrawableRes val imageResId: Int
) {
    init {
        require(options.size == 4) { "Each question must have exactly four options." }
        require(answer in options) { "The answer must be one of the available options." }
    }
}

data class AnswerResult(
    val isCorrect: Boolean,
    val correctAnswer: String,
    val explanation: String
)

object QuizScorer {
    const val PointsPerCorrectAnswer = 10

    fun score(currentScore: Int, isCorrect: Boolean): Int {
        return if (isCorrect) currentScore + PointsPerCorrectAnswer else currentScore
    }

    fun percentage(score: Int, questionCount: Int): Int {
        if (questionCount == 0) return 0
        return (score * 100) / (questionCount * PointsPerCorrectAnswer)
    }
}
