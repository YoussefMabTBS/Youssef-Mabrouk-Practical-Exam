package com.youssefmabrouk.heritagequest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.youssefmabrouk.heritagequest.data.CategoryId
import com.youssefmabrouk.heritagequest.ui.screens.CategorySelectionScreen
import com.youssefmabrouk.heritagequest.ui.screens.DifficultyScreen
import com.youssefmabrouk.heritagequest.ui.screens.MainMenuScreen
import com.youssefmabrouk.heritagequest.ui.screens.QuizScreen
import com.youssefmabrouk.heritagequest.ui.screens.ResultsScreen
import com.youssefmabrouk.heritagequest.ui.screens.SplashScreen
import com.youssefmabrouk.heritagequest.viewmodel.QuizViewModel

object Routes {
    // Route names used by the navigation graph.
    const val Splash = "splash"
    const val Menu = "menu"
    const val Categories = "categories"
    const val Difficulty = "difficulty"
    const val Quiz = "quiz"
    const val Results = "results"
}

@Composable
fun HeritageQuestApp(
    viewModel: QuizViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val state by viewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash
    ) {
        // Each composable here is one app screen.
        composable(Routes.Splash) {
            SplashScreen(
                onFinished = {
                    navController.navigate(Routes.Menu) {
                        popUpTo(Routes.Splash) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Menu) {
            MainMenuScreen(
                onStart = { navController.navigate(Routes.Categories) }
            )
        }

        composable(Routes.Categories) {
            CategorySelectionScreen(
                state = state,
                onBack = { navController.popBackStack() },
                onCategorySelected = { categoryId: CategoryId ->
                    viewModel.selectCategory(categoryId)
                    navController.navigate(Routes.Difficulty)
                }
            )
        }

        composable(Routes.Difficulty) {
            DifficultyScreen(
                state = state,
                onBack = { navController.popBackStack() },
                onDifficultySelected = viewModel::selectDifficulty,
                onToggleTimer = viewModel::toggleTimer,
                onToggleSound = viewModel::toggleSound,
                onToggleHaptics = viewModel::toggleHaptics,
                onPlay = {
                    viewModel.startQuiz()
                    navController.navigate(Routes.Quiz)
                }
            )
        }

        composable(Routes.Quiz) {
            QuizScreen(
                state = state,
                onBack = { navController.popBackStack() },
                onAnswerSelected = viewModel::submitAnswer,
                onNext = viewModel::nextQuestion,
                onFinish = { navController.navigate(Routes.Results) }
            )
        }

        composable(Routes.Results) {
            ResultsScreen(
                state = state,
                onRestart = {
                    viewModel.restart()
                    navController.navigate(Routes.Quiz) {
                        popUpTo(Routes.Quiz) { inclusive = true }
                    }
                },
                onHome = {
                    navController.navigate(Routes.Menu) {
                        popUpTo(Routes.Menu) { inclusive = true }
                    }
                }
            )
        }
    }
}
