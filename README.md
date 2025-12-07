 Quizzy – Android Developer Assignment

A simple Android application built as part of the Quizzy Android Developer Assignment.
The app implements a complete Login → Dashboard → Logout flow following MVVM architecture, clean code principles, and the Figma UI.

Firebase Email/Password Authentication

Login validation and error handling

Secure sign-in and sign-out

Redirects user to Dashboard on successful login

 Dashboard (Home Screen)

Fully implemented using the provided Figma design

Fetches JSON data from the given Mock API

Displays:

Welcome Section

Today’s Summary

Weekly Streak

Accuracy View

Performance Insights

 Logout Flow

User can log out from Settings inside Dashboard

Clears session (Firebase sign-out)

Navigates back to Login screen using Jetpack Navigation

 Tech Stack
Layer	Technology
Language	Kotlin
UI	Jetpack Compose
Architecture	MVVM + Clean UI State
Navigation	Navigation Compose
DI	Koin
Authentication	Firebase Auth
Networking	Ktor Client / Retrofit (whichever you used)
State Management	StateFlow, ViewModel, collectAsState()
 Architecture Overview
app/
 ├─ data/
 │   ├─ model/
 │   ├─ repository/
 │   └─ network/
 ├─ domain/
 ├─ ui/
 │   ├─ login/
 │   ├─ dashboard/
 │   ├─ components/
 │   └─ navigation/
 ├─ di/ (Koin Modules)
 └─ MainActivity.kt


ViewModels handle UI state

Repository handles data fetch (Mock API)

Navigation manages Login → Dashboard → Logout

UI is fully Compose-based with state-driven recomposition
