# YoussefMabrouk

## Project overview

YoussefMabrouk is an Android quiz game made with Kotlin and Jetpack Compose.

The app is called Tunisia Heritage Quest. The player looks at real photos and chooses the correct answer from four options.

The app shows all 6 heritage categories, but only 2 categories are playable:

- Modern Heritage
- Natural & Mixed Sites

Each playable category has questions for Easy, Medium, and Hard difficulty.

## Architecture explanation

The project uses a simple MVVM structure.

- `data`: contains the quiz categories, questions, answers, and score logic.
- `viewmodel`: contains `QuizViewModel`, which manages the quiz state.
- `ui`: contains the Compose screens and reusable UI components.
- `navigation`: contains the navigation between screens.

The app uses:

- Jetpack Compose for the UI.
- Navigation Compose for moving between screens.
- ViewModel and StateFlow for app state.
- Local drawable images for all quiz and category photos.
- Unit, ViewModel, and Navigation tests.

## Setup instructions

1. Open the `YoussefMabrouk` folder in Android Studio.
2. Wait for Gradle sync to finish.
3. Choose an emulator or connect an Android phone.
4. Press Run to start the app.

To run tests:

- Run unit tests from `app/src/test`.
- Run the navigation test from `app/src/androidTest` using an emulator or real device.
