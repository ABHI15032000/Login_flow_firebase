package com.example.pwloginapp.ui.theme.home

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pwloginapp.data.model.PerformanceTopic
import com.example.pwloginapp.data.model.QuizStreak
import com.example.pwloginapp.data.model.Student
import com.example.pwloginapp.data.model.StudentDashboardResponse
import com.example.pwloginapp.data.model.TodaySummary
import org.koin.androidx.compose.koinViewModel
import com.example.pwloginapp.R
import com.example.pwloginapp.data.model.OverallAccuracy

class StudentDashboardScreen {


    private val CardBackgroundColor = Color(0xFFFCF7FF)
    private val CardBorderColor = Color(0xFF996EB5)
    private val BorderThickness = 0.5.dp

    private val PurplePrimary = Color(0xFF673AB7)

    @Composable
    fun DashboardScreen(viewModel: DashBoardViewModel = koinViewModel()) {
        val state by viewModel.uiState.collectAsState()

        var showNotificationsSettings by rememberSaveable { mutableStateOf(false) }

        if (showNotificationsSettings) {
            // 2. If true, show the second screen
            NotificationScreen(
                onBackClick = { showNotificationsSettings = false }, // Back button sets state back to false
                onLogoutClick = {  }
            )
        }else{
            when (state) {
                is DashboardUiState.Loading -> {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is DashboardUiState.Success -> {
                    val data = (state as DashboardUiState.Success).data
                    DashboardContent(data,
                        onNotificationClick = { showNotificationsSettings = true } )
                }

                is DashboardUiState.Error -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Error: ${(state as DashboardUiState.Error).message}")
                    }

                }

            }
        }



    }

    @Composable
    fun DashboardContent(data: StudentDashboardResponse,
                         onNotificationClick: () -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(8.dp)
        ) {

            WelcomeSection(student = data.student,
                onNotificationClick = onNotificationClick)

            Spacer(Modifier.height(24.dp))

//            StatusCardsRow()

            TodaySummaryCard(data)

            Spacer(Modifier.height(24.dp))

            WeeklyStreakRow(data.weeklyOverview.quizStreak, data.weeklyOverview.overallAccuracy)

//            Spacer(Modifier.height(24.dp))
//
//            OverallAccuracySection(data.weeklyOverview.overallAccuracy.percentage)
//
//            Spacer(Modifier.height(24.dp))
//
//            PerformanceByTopicSection(data.weeklyOverview.performanceByTopic)

        }
    }


//    @Composable
//    fun StatusCardsRow(
//        cards: List<StatusCardData>,
//        modifier: Modifier = Modifier
//    ) {
//        Row(
//            modifier = modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp, vertical = 8.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.Top
//        ) {
//            cards.forEach { card ->
//                // Apply equal weight to each card for even spacing
//                StatusCard(data = card, modifier = Modifier.weight(1f))
//                // Add a small spacer between cards
//                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
//            }
//        }
//    }

    @Composable
    fun TodaySummaryCard(data : StudentDashboardResponse) {

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Todays Summary",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
            )

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(16.dp), // Larger rounded corners
                color =  CardBackgroundColor ,
                border = BorderStroke(BorderThickness, CardBorderColor)// Very light purple background

            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.focus_image),
                        contentDescription = "Focus Character Illustration",
                        modifier = Modifier.size(96.dp),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = data.todaySummary.mood,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = CardBorderColor
                        ),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Description Text ("Struggles with Apply-level Math today.")
                    Text(
                        text = data.todaySummary.description,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Normal,
                            color = Color.Black.copy(alpha = 0.7f)
                        ),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black, // Dark button background
                            contentColor = Color.White
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                            Text(
                                text = data.todaySummary.recommendedVideo.actionText,
                                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold)
                            )
                        }

                    }

                }

            }
        }
//        Card(
//            shape = RoundedCornerShape(20.dp),
//            modifier = Modifier.fillMaxWidth(),
//            elevation = CardDefaults.cardElevation(6.dp)
//        ) {
//            Column(modifier = Modifier.padding(16.dp)) {
//                Text(summary.mood, fontWeight = FontWeight.Bold, fontSize = 20.sp)
//                Spacer(Modifier.height(8.dp))
//                Text(summary.description, color = Color.Gray)
//
//                Spacer(Modifier.height(16.dp))
//
//                Button(
//                    onClick = { /* open video */ },
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = RoundedCornerShape(12.dp)
//                ) {
//                    Text(summary.recommendedVideo.actionText)
//                }
//            }
//        }
    }

    @Composable
    fun WelcomeSection(student: Student,
                       onNotificationClick: () -> Unit
                       ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Hello ${student.name}!",
                    style =  MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        color = Color.Black
                    ),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = student.`class`,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold, // 600 (SemiBold)
                        fontSize = 14.sp, // 14px
                        lineHeight = 20.sp, // 20px
                        color = Color.Gray
                    ),
                    color = Color.Gray
                )
            }

            IconButton(
                onClick = onNotificationClick,
                modifier = Modifier.padding(start = 8.dp) // Spacing from the text column
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Navigate to Notifications & Settings", // Updated description
                    tint = Color.Black, // Dark icon color
                )
            }
        }



    }


    @Composable
    fun PerformanceByTopicSection(list: List<PerformanceTopic>) {
        Text("Performance by Topics", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.height(12.dp))

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

            list.forEach { item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(item.topic)

                    if (item.trend == "up") {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = null,
                            tint = Color(0xFF4CAF50)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = Color(0xFFF44336)
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun OverallAccuracySection(accuracy: Int) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Overall Accuracy", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text("$accuracy%", fontSize = 42.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(Modifier.height(4.dp))
                Text("Correct answers", color = Color.Gray)
            }
        }
    }

    @Composable
    fun WeeklyStreakRow(
        quizStreak: List<QuizStreak>,
        accuracy: OverallAccuracy
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Section 1: Quiz Streak
                QuizStreakView(quizStreak)
                Spacer(Modifier.height(16.dp))
                Divider()

                // Section 2: Accuracy
                AccuracyView(accuracy)
                Spacer(Modifier.height(16.dp))
                Divider()

                // Section 3: Performance by Topic
//                PerformanceByTopicView(performanceData = List(Per))
            }
        }
    }
    @Composable
    fun QuizStreakView(streak: List<QuizStreak>) {
        // Top Row: Title + Icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Quiz Streak", style = MaterialTheme.typography.titleLarge)
            // Placeholder for the icon (using a standard Icon)
//            Icon(
//                imageVector = painterResource(R.drawable.ic_launcher_foreground),
//                contentDescription = "Quiz Information"
//            )
        }

        Spacer(Modifier.height(8.dp))

        // Bottom Row: The streak icons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            streak.forEach { item ->
                DayStreakItem(day = item.day, isDone = item.status == "done")
            }
        }
    }

    // Sub-Composable for each day's icon
    @Composable
    fun DayStreakItem(day: String, isDone: Boolean) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .border(
                    width = 1.5.dp,
                    color = if (isDone) Color.Green else Color.LightGray,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isDone) {
                Icon(Icons.Default.Check, contentDescription = day, tint = Color.White)
            } else {
                Text(day, color = Color.Gray)
            }
        }
    }

    @Composable
    fun AccuracyView(accuracy: OverallAccuracy) {
        // Top Row: Title + Icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Accuracy", style = MaterialTheme.typography.titleLarge)
            // Placeholder for the target icon
//            Icon(
//                imageVector = painterResource(R.drawable.focus_image),
//                contentDescription = "Target",
//                tint = Color.Red
//            )
        }

        Spacer(Modifier.height(8.dp))

        // Percentage Text
        Text(
            text = "${accuracy.percentage}% correct",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(4.dp))

        // Progress Bar
        val progress = accuracy.percentage / 100f
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth().height(8.dp),
            color = Color(0xFFE53935), // Red color similar to the image
            trackColor = Color.LightGray
        )
    }


    @Composable
    fun PerformanceByTopicView(
        // Accept the list of performance data
        performanceData: List<PerformanceTopic>
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            // 1. Heading Row (Title, Divider Line, and Icon)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Performance by Topic",
                    style = MaterialTheme.typography.titleLarge
                )

                // Bar Chart Icon Placeholder
//                Icon(
//                    imageVector = painterResource(R.drawable.focus_image),
//                    contentDescription = "Performance Chart",
//                    tint = MaterialTheme.colorScheme.primary
//                )
            }

            // The thin horizontal line shown under the heading in the image
            HorizontalDivider(modifier = Modifier.padding(top = 4.dp))

            Spacer(Modifier.height(8.dp))

            // 2. Display the list of topics
            Column(modifier = Modifier.fillMaxWidth()) {
                performanceData.forEach { item ->
                    TopicItemRow(item)
                }
            }
        }
    }

    // Composable for a single topic row
    @Composable
    fun TopicItemRow(item: PerformanceTopic) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Topic Name
            Text(item.topic, style = MaterialTheme.typography.bodyMedium)

            // Trend Icon
//            val trendIcon = if (item.trend == "up") Icons.Default.ArrowUpward else Icons.Default.ArrowDownward
            val trendColor = if (item.trend == "up") Color.Green else Color.Red

//            Icon(
//                imageVector = trendIcon,
//                contentDescription = "${item.topic} trend is ${item.trend}",
//                tint = trendColor,
//                modifier = Modifier.size(16.dp)
//            )
        }
    }
}