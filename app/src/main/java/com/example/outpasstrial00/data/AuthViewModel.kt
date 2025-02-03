package com.example.outpasstrial00.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {
    private val userRepository: UserRepository =
        UserRepository(
            FirebaseAuth.getInstance(),
            Injection.instance()
        )

    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult: LiveData<Result<Boolean>> get() = _authResult

    fun login(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _authResult.value = userRepository.login(email, password)
        }
    }

    fun signUp(
        email: String,
        password: String,
        name: String,
        roll: String,
        phone: String
    ) {
        viewModelScope.launch {
            _authResult.value = userRepository.signUp(email, password, name, roll, phone)
        }
    }

    fun isUserLoggedIn(): Boolean {
        return userRepository.isUserLoggedIn()
    }

    fun uploadUserData(reason: String){
        viewModelScope.launch {
            _authResult.value = userRepository.uploadUserData(reason)
        }
    }

    fun updateComingInTime() {
        viewModelScope.launch {
            _authResult.value = userRepository.updateComingInTime()
        }
    }

    fun logout() {
        _authResult.value = userRepository.logout()
    }
}