package com.example.pwloginapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pwloginapp.ui.theme.PwLoginAppTheme
import com.example.pwloginapp.ui.theme.home.StudentDashboardScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //QuizzyApp()
            QuizzyApp()
//            PwLoginAppTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    StudentDashboardScreen().DashboardScreen()
//                }
//            }
        }
    }
}

