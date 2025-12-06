package com.example.pwloginapp.ui.theme.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pwloginapp.data.repository.DashboardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashBoardViewModel(
    private val repository: DashboardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState: StateFlow<DashboardUiState> = _uiState

    init {
        loadDashboardData()
    }

    private fun loadDashboardData() {
        viewModelScope.launch {
            try {
                _uiState.update { DashboardUiState.Loading }

                val result = repository.fetchStudentDashboard()

                _uiState.update {
                    DashboardUiState.Success(result)
                }
            } catch (e: Exception) {
                _uiState.update {
                    DashboardUiState.Error(e.message ?: "Unknown error")
                }
            }
        }
    }
}
