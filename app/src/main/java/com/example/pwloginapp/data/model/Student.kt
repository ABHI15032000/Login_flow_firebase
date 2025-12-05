package com.example.pwloginapp.data.model

data class Student(
    val name: String,
    val `class`: String,
    val availability: Availability,
    val quiz: Quiz,
    val accuracy: Accuracy
)

data class Availability(val status: String)
data class Quiz(val attempts: Int)
data class Accuracy(val current: String)