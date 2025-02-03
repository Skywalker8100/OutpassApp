package com.example.outpasstrial00

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.outpasstrial00.data.AuthViewModel
import com.example.outpasstrial00.screens.HomeScreen
import com.example.outpasstrial00.screens.LoginScreen
import com.example.outpasstrial00.screens.Screens
import com.example.outpasstrial00.screens.SignUpScreen
import com.example.outpasstrial00.screens.SplashScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(Screens.SplashScreen.route)
        {
            SplashScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable(Screens.SignUpScreen.route)
        {
            SignUpScreen(
                authViewModel = authViewModel,
                onNavigateToLogin = {
                    navController.navigate(Screens.LoginScreen.route)
                }
            )
        }
        composable(Screens.LoginScreen.route)
        {
            LoginScreen(
                onNavigateToSignUp = {
                    navController.navigate(Screens.SignUpScreen.route)
                },
                onSignInSuccess = {
                    navController.navigate(Screens.HomeScreen.route)
                },
                authViewModel = authViewModel
            )
        }
        composable(Screens.HomeScreen.route)
        {
            HomeScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
    }
}