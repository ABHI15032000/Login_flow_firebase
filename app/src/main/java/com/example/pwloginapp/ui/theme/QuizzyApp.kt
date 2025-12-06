package com.example.pwloginapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.pwloginapp.ui.theme.home.StudentDashboardScreen
import com.example.pwloginapp.ui.theme.login.LoginScreen

@Composable
fun QuizzyApp() {
    var loggedIn by remember { mutableStateOf(false) }

    if (loggedIn) {
        StudentDashboardScreen().DashboardScreen()
    } else {
        LoginScreen(
            onLoginSuccess = { loggedIn = true }
        )
    }
}