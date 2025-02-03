package com.example.outpasstrial00.data

data class UserData(
    val reason: String,
    val timestamp: Long = System.currentTimeMillis(),
    val inTime: Long? = null
)