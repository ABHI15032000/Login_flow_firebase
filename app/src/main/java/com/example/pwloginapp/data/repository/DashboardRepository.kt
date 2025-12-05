package com.example.pwloginapp.data.repository

import com.example.pwloginapp.data.model.StudentDashboardResponse

interface  DashboardRepository {
    suspend fun fetchStudentDashboard() : StudentDashboardResponse
}