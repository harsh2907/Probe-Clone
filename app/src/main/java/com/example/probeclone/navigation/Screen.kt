package com.example.probeclone.navigation

sealed class Screen(val route:String) {
    data object HomeScreen:Screen("home")
    data object QuestionScreen:Screen("question")
}