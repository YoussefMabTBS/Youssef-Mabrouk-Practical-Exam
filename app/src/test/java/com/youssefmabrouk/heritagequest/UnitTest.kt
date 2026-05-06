package com.youssefmabrouk.heritagequest

import com.youssefmabrouk.heritagequest.data.CategoryId
import com.youssefmabrouk.heritagequest.data.HeritageRepository
import com.youssefmabrouk.heritagequest.data.QuizScorer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UnitTest {
    private val repository = HeritageRepository()

    @Test
    fun onlyTwoCategoriesPlayable() {
        assertEquals(6, repository.categories.size)

        val playableIds = repository.categories.filter { it.isPlayable }.map { it.id }.toSet()
        assertEquals(setOf(CategoryId.ModernHeritage, CategoryId.NaturalMixed), playableIds)
    }

    @Test
    fun playableQuestionsAreValid() {
        repository.categories.filter { it.isPlayable }.forEach { category ->
            com.youssefmabrouk.heritagequest.data.Difficulty.entries.forEach { difficulty ->
                val questions = repository.questionsFor(category.id, difficulty)

                assertTrue(questions.size in 5..10)
                assertEquals(category.questionCount, questions.size)
                assertTrue(questions.all { it.options.size == 4 })
                assertTrue(questions.all { it.answer in it.options })
            }
        }
    }

    @Test
    fun scorerWorks() {
        val score = QuizScorer.score(currentScore = 20, isCorrect = true)

        assertEquals(30, score)
        assertEquals(50, QuizScorer.percentage(score = 30, questionCount = 6))
    }
}
