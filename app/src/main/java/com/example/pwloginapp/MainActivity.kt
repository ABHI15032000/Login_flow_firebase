package com.example.pwloginapp

import LoginScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.pwloginapp.ui.theme.home.StudentDashboardScreen
import com.example.pwloginapp.ui.theme.login.RegistrationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var isLoggedIn by rememberSaveable { mutableStateOf(false) }
            var showRegister by rememberSaveable { mutableStateOf(false) }

            when {
                isLoggedIn -> {
                    StudentDashboardScreen().DashboardScreen(onLogout = { isLoggedIn = false }
                    )
                }

                showRegister -> {
                    RegistrationScreen(
                        onRegisterSuccess = {
                            showRegister = false   // Go to login screen automatically
                        },
                        onBackToLogin = {
                            showRegister = false
                        }
                    )
                }

                else -> {
                    LoginScreen(
                        onLoginSuccess = { isLoggedIn = true },
                        onRegisterClick = { showRegister = true }
                    )
                }
            }
        }
    }
}


