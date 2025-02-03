package com.example.outpasstrial00.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.outpasstrial00.data.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    authViewModel: AuthViewModel
){

    val isUserLoggedIn by remember {
        mutableStateOf(authViewModel.isUserLoggedIn())
    }
//
//    LaunchedEffect(Unit) {
//        delay(1000)
//        if (isUserLoggedIn) {
//            navController.navigate(Screens.HomeScreen.route)
//            {
//                popUpTo(Screens.SplashScreen.route) {inclusive = true}
//            }
//        } else {
//            navController.navigate(Screens.LoginScreen.route)
//            {
//                popUpTo(Screens.SplashScreen.route) {inclusive = true}
//            }
//        }
//    }
//
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = "Splash Screen"
//        )
//    }

//    val isUserLoggedIn by authViewModel.isUserLoggedIn().collectAsState(initial = false)

    val alpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 2000)
    )

    LaunchedEffect(Unit) {
        delay(3000) // Show the splash screen for 3 seconds
        if (isUserLoggedIn) {
            navController.navigate(Screens.HomeScreen.route) {
                popUpTo(Screens.SplashScreen.route) { inclusive = true }
            }
        } else {
            navController.navigate(Screens.LoginScreen.route) {
                popUpTo(Screens.SplashScreen.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Welcome to Outpass App",
            color = Color.Black,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.alpha(alpha)
        )
    }

}