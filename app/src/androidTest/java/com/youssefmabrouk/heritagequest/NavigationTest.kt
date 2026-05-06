package com.youssefmabrouk.heritagequest

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun fullQuizFlowReturnsHome() {
        openQuiz()
        finishQuiz()

        waitForText("Quiz Results")
        clickText("Main Menu")

        waitForText("Start Quiz")
        assertTextVisible("Tunisia Heritage Quest")
    }

    @Test
    fun playAgainOpensQuiz() {
        openQuiz()
        finishQuiz()

        waitForText("Quiz Results")
        clickText("Play Again")

        waitForText("Image Quiz")
    }

    @Test
    fun backButtonsWork() {
        openCategories()
        clickText("Back")
        waitForText("Start Quiz")

        openCategories()
        selectModernCategory()
        waitForText("Difficulty & Settings")
        clickText("Back")
        waitForText("Choose Category")

        selectModernCategory()
        clickText("Play Medium")
        waitForText("Image Quiz")
        clickText("Back")
        waitForText("Difficulty & Settings")
    }

    private fun openQuiz() {
        openCategories()
        selectModernCategory()
        waitForText("Difficulty & Settings")
        clickText("Play Medium")
        waitForText("Image Quiz")
    }

    private fun openCategories() {
        waitForText("Start Quiz")
        clickText("Start Quiz")
        waitForText("Choose Category")
        assertTextVisible("Natural & Mixed Sites")
    }

    private fun selectModernCategory() {
        composeRule.onNodeWithTag("category_ModernHeritage").performScrollTo().performClick()
    }

    private fun finishQuiz() {
        repeat(5) {
            answerCurrentQuestion()
            waitForText("Next Question")
            clickText("Next Question")
            waitForText("Image Quiz")
        }

        answerCurrentQuestion()
        waitForText("See Results")
        clickText("See Results")
    }

    private fun answerCurrentQuestion() {
        composeRule.onAllNodesWithText("A.", substring = true)[0].performScrollTo().performClick()
    }

    private fun clickText(text: String) {
        composeRule.onNodeWithText(text).performScrollTo().performClick()
    }

    private fun waitForText(text: String) {
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithText(text).fetchSemanticsNodes().isNotEmpty()
        }
    }

    private fun assertTextVisible(text: String) {
        assertTrue(composeRule.onAllNodesWithText(text).fetchSemanticsNodes().isNotEmpty())
    }
}
