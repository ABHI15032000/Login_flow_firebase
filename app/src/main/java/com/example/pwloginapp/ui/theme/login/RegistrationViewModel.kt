package com.example.pwloginapp.ui.theme.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pwloginapp.data.auth.AuthRepository
import kotlinx.coroutines.launch

class RegistrationViewModel(private val repo: AuthRepository) : ViewModel() {

    var uiState by mutableStateOf(RegistrationUiState())
        private set

    fun onEmailChange(new: String) {
        uiState = uiState.copy(email = new)
    }

    fun onPasswordChange(new: String) {
        uiState = uiState.copy(password = new)
    }

    fun onConfirmPasswordChange(new: String) {
        uiState = uiState.copy(confirmPassword = new)
    }

    fun register() {
        if (uiState.password != uiState.confirmPassword) {
            uiState = uiState.copy(error = "Passwords do not match")
            return
        }

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)

            val result = repo.register(uiState.email, uiState.password)

            uiState = if (result.isSuccess) {
                uiState.copy(isLoading = false, isSuccess = true)
            } else {
                uiState.copy(isLoading = false, error = result.exceptionOrNull()?.message)
            }
        }
    }
}
