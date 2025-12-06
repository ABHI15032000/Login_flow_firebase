package com.example.pwloginapp.ui.theme.home

import com.example.pwloginapp.data.model.StudentDashboardResponse

sealed class DashboardUiState {
    object Loading : DashboardUiState()
    data class Success(val data: StudentDashboardResponse) : DashboardUiState()
    data class Error(val message: String) : DashboardUiState()
}
//Represents various states of the UI