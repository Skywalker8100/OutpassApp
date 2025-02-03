package com.example.outpasstrial00.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.outpasstrial00.data.AuthViewModel
import com.example.outpasstrial00.ui.theme.OutpassTrial00Theme

@Composable
fun HomeScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var showPopUp by remember {
        mutableStateOf(false)
    }
    var out by remember {
        mutableStateOf(false)
    }

    val snackbarHostState = remember { SnackbarHostState() }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if(showPopUp) {
            ReasonBox(
                authViewModel = authViewModel,
                onDismissRequest = {showPopUp = false},
                onSubmit = {
                    out = true
                    showPopUp = false
                }
            )
        }

        AnimatedVisibility(
            visible = !out
        ) {
            ElevatedCard(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                onClick = {
                    showPopUp = true
                },
                modifier = Modifier
                    .fillMaxHeight(
                        0.15f
                    )
                    .fillMaxWidth(
                        0.8f
                    )

            ) {
                Text(
                    text = "Going Out",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = 42.dp,
                            start = 72.dp
                        ),
                    fontSize = 34.sp
                )
            }
        }

        AnimatedVisibility(
            visible = out
        ) {
            ElevatedCard(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                ),
                onClick = {
                    authViewModel.updateComingInTime()
                    out = !out
                },
                modifier = Modifier
                    .fillMaxHeight(
                        0.15f
                    )
                    .fillMaxWidth(
                        0.8f
                    )

            ) {
                Text(
                    text = "Coming In",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = 42.dp,
                            start = 72.dp
                        ),
                    fontSize = 34.sp
                )
            }
        }
        Spacer(
            modifier = Modifier.height(22.dp)
        )
        Button(
            onClick = {
                authViewModel.logout()
                navController.navigate(Screens.LoginScreen.route)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Logout")
        }
    }
}

@Composable
fun ReasonBox(
    onDismissRequest: () -> Unit,
    authViewModel: AuthViewModel,
    onSubmit: () -> Unit
) {
    var reason by remember { mutableStateOf("") }
    AlertDialog(
        title = {
            Text(
                text = "Reason"
            )
        },
        onDismissRequest = {onDismissRequest()},
        confirmButton = {
            Button(
                onClick = {
                    authViewModel.uploadUserData(reason)
                    onSubmit()
                }
            ) {
                Text(
                    text = "Submit"
                )
            }
        },
        text = {
            Column() {
                OutlinedTextField(
                    value = reason,
                    onValueChange = {reason = it}
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    OutpassTrial00Theme {
//        HomeScreen()
    }
}