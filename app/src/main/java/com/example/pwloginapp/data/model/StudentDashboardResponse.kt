package com.example.pwloginapp.data.model

data class StudentDashboardResponse(
    val student: Student,
    val todaySummary: TodaySummary,
    val weeklyOverview: WeeklyOverview
)
