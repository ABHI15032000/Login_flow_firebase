package com.example.pwloginapp.data.repository

import com.example.pwloginapp.data.model.StudentDashboardResponse
import com.example.pwloginapp.data.remote.ApiService

class DashboardRepositoryImpl(private val api : ApiService) : DashboardRepository {
    override suspend fun fetchStudentDashboard(): StudentDashboardResponse {
        return api.getStudentDashboard()
    }

}