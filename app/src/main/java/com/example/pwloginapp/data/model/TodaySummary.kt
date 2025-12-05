package com.example.pwloginapp.data.model

data class TodaySummary(
    val mood: String,
    val description: String,
    val recommendedVideo: RecommendVideo,
    val characterImage: String
)

data class RecommendVideo(
    val title: String,
    val actionText: String
)