package com.example.pwloginapp.data.model

data class WeeklyOverview(
    val quizStreak: List<QuizStreak>,
    val overallAccuracy: OverallAccuracy,
    val performanceByTopic: List<PerformanceTopic>
)

data class QuizStreak(val day: String, val status: String)

data class OverallAccuracy(val percentage: Int, val label: String)

data class PerformanceTopic(val topic: String, val trend: String)

