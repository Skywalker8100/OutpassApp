package com.example.outpasstrial00.screens

sealed class Screens(val route: String) {
    object LoginScreen : Screens("login screen")
    object SignUpScreen : Screens("sign up screen")
    object HomeScreen : Screens("home screen")
    object SplashScreen: Screens("splash screen")
}