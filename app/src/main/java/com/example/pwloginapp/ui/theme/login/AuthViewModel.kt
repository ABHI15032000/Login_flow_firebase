package com.example.pwloginapp.ui.theme.login

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pwloginapp.data.auth.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repo: AuthRepository) : ViewModel() {


    var uiState by mutableStateOf(AuthUiState())
        private set

    fun onEmailChange(new: String) {
        uiState = uiState.copy(email = new)
    }

    fun onPasswordChange(new: String) {
        uiState = uiState.copy(password = new)
    }

    fun login() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)

            val result = repo.login(uiState.email, uiState.password)

            uiState = if (result.isSuccess) {
                uiState.copy(isLoading = false, isSuccess = true)
            } else {
                uiState.copy(
                    isLoading = false,
                    error = result.exceptionOrNull()?.message
                )
            }
        }
    }
    fun logout() {
        repo.logout()
    }
}