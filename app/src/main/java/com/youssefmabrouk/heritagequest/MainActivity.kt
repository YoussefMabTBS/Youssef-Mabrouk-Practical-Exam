package com.youssefmabrouk.heritagequest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.youssefmabrouk.heritagequest.ui.navigation.HeritageQuestApp
import com.youssefmabrouk.heritagequest.ui.theme.HeritageQuestTheme
import com.youssefmabrouk.heritagequest.viewmodel.QuizViewModel

class MainActivity : ComponentActivity() {
    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reportLifecycle("created")
        setContent {
            HeritageQuestTheme {
                HeritageQuestApp(viewModel = quizViewModel)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        reportLifecycle("started")
    }

    override fun onResume() {
        super.onResume()
        reportLifecycle("resumed")
    }

    override fun onPause() {
        reportLifecycle("paused")
        super.onPause()
    }

    override fun onStop() {
        reportLifecycle("stopped")
        super.onStop()
    }

    override fun onDestroy() {
        reportLifecycle("destroyed")
        super.onDestroy()
    }

    private fun reportLifecycle(stage: String) {
        Log.d("YoussefMabroukLifecycle", "Activity lifecycle stage: $stage")
        quizViewModel.recordLifecycle(stage)
    }
}
