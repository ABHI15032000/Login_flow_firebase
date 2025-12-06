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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel


@Composable
fun RegistrationScreen(
    onRegisterSuccess: () -> Unit,
    onBackToLogin: () -> Unit,
    viewModel: RegistrationViewModel = koinViewModel()
) {
    val ui = viewModel.uiState

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Create an Account",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    OutlinedTextField(
                        value = ui.email,
                        onValueChange = viewModel::onEmailChange,
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = ui.password,
                        onValueChange = viewModel::onPasswordChange,
                        label = { Text("Password") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = ui.confirmPassword,
                        onValueChange = viewModel::onConfirmPasswordChange,
                        label = { Text("Confirm Password") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = { viewModel.register() },
                        enabled = !ui.isLoading,
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(56.dp)
                            .offset(y = 20.dp),
                        shape = RoundedCornerShape(50)
                    ) {
                        if (ui.isLoading) CircularProgressIndicator(color = Color.White)
                        else Text("Register", fontSize = 18.sp)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    TextButton(onClick = onBackToLogin) {
                        Text("Already have an account? Login")
                    }

                    if (ui.error != null) {
                        Text(ui.error!!, color = Color.Red)
                    }
                }
            }
        }
    }

    if (ui.isSuccess) {
        LaunchedEffect(Unit) {
            onRegisterSuccess()
        }
    }
}
