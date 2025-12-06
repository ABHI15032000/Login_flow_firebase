package com.example.pwloginapp.ui.theme.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.pwloginapp.ui.theme.login.AuthViewModel
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    onBackClick: () -> Unit,
    onLogoutClick: () -> Unit,
    viewModel: AuthViewModel = koinViewModel()
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notifications & Settings") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        // Using LazyColumn for efficient scrolling and list rendering
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // --- NOTIFICATIONS SECTION ---
            item {
                SectionHeader("Notifications")
            }

            // Notification Items (using hardcoded data for demonstration)
            item {
                NotificationItem(
                    title = "Missed quiz in physics yesterday",
                    time = "2 hours ago",
                    backgroundColor = Color(0xFFFFDDBB) // Light Orange
                )
            }
            item {
                NotificationItem(
                    title = "Badge earned",
                    time = "8 hours ago",
                    backgroundColor = Color(0xFFE4CFFF) // Light Purple
                )
            }
            item {
                NotificationItem(
                    title = "Teacher Note",
                    time = "1 day ago",
                    backgroundColor = Color(0xFFC8F6CD) // Light Green
                )
            }

            // --- SETTINGS SECTION ---
            item {
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader("Settings")
            }

            // Setting Items
            item {
                SettingItem(
                    icon = Icons.Outlined.Person,
                    title = "Switch Child",
                    subtitle = "Change active child profile",
                    onClick = { /* Handle Switch Child navigation/logic */ }
                )
            }
            item {
                SettingItem(
                    icon = Icons.Outlined.Warning,
                    title = "Language",
                    subtitle = "English",
                    onClick = { /* Handle Language selection dialog/screen */ }
                )
            }
            item {
                SettingItem(
                    icon = Icons.Outlined.AccountCircle,
                    title = "Logout",
                    subtitle = "Sign out of your account",
                    onClick = { viewModel.logout()
                        onLogoutClick()}// Trigger the lambda passed from the parent
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

// --- Reusable Item Composables ---

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = Color.Black,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun NotificationItem(title: String, time: String, backgroundColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor.copy(alpha = 0.6f)) // Use opacity for a lighter look
                .clickable { /* Handle notification click */ }
                .padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
            Text(
                text = time,
                style = MaterialTheme.typography.bodySmall,
                color = Color.DarkGray,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

@Composable
fun SettingItem(icon: ImageVector, title: String, subtitle: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null, // Content description for icons should be handled if text isn't present
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary // Use primary color for icons
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

