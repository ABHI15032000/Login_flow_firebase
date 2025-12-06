package com.example.pwloginapp.ui.theme.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val LoginCardShape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp, bottomStart = 64.dp, bottomEnd = 64.dp)

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit, // Callback for successful navigation
    // ViewModel parameter would be added here later
) {
    // 1. State for input fields (using empty strings for now)
    var phoneNo by remember { mutableStateOf("") }
    var studentId by remember { mutableStateOf("") }
    val isLoading by remember { mutableStateOf(false) } // State for loading indicator

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black // Background color from your image
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Placeholder for the 3D avatars (Image part of the screen)
            Box(
                modifier = Modifier
                    .weight(1f) // Takes up upper half
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                // In a real app, you'd place your 3D avatar images here
                Text(
                    "Welcome to Quizzy!",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                //             }

                // 2. White Card Section
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = LoginCardShape,
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(32.dp))
                        Text(
                            text = "Let's Get you Signed in",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        // School ID Input Field
                        OutlinedTextField(
                            value = phoneNo,
                            onValueChange = { phoneNo = it },
                            label = { Text("Phone No") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        // Student ID Input Field (Password field would typically be used here for a real password)
                        OutlinedTextField(
                            value = studentId,
                            onValueChange = { studentId = it },
                            label = { Text("Student ID") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )
                        Spacer(modifier = Modifier.height(32.dp))

                        // Sign In Button
                        Button(
                            onClick = {
                                // TODO: Call ViewModel to authenticate with Firebase
                                if (!isLoading) {
                                    // For now, simulate success instantly
                                    onLoginSuccess()
                                }
                            },
                            enabled = !isLoading,
                            modifier = Modifier
                                .fillMaxWidth(0.6f) // Takes 60% of the width
                                .height(56.dp)
                                .offset(y = 20.dp), // Pushes the button into the curve
                            shape = RoundedCornerShape(50)
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator(color = Color.White)
                            } else {
                                Text("Sign in", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                }
            }
        }
    }
}