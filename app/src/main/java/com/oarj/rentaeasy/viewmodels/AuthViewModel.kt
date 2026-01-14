package com.oarj.rentaeasy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oarj.rentaeasy.models.User
import com.oarj.rentaeasy.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val repository = AuthRepository()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        loadCurrentUser()
    }

    private fun loadCurrentUser() {
        viewModelScope.launch {
            _currentUser.value = repository.getCurrentUser()
        }
    }

    fun register(email: String, password: String, name: String, userType: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            val result = repository.register(email, password, name, userType)
            result.onSuccess { user ->
                _currentUser.value = user
                onSuccess()
            }.onFailure { exception ->
                _errorMessage.value = exception.message
            }

            _isLoading.value = false
        }
    }

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            val result = repository.login(email, password)
            result.onSuccess { user ->
                _currentUser.value = user
                onSuccess()
            }.onFailure { exception ->
                _errorMessage.value = exception.message
            }

            _isLoading.value = false
        }
    }

    fun logout(onSuccess: () -> Unit) {
        repository.logout()
        _currentUser.value = null
        onSuccess()
    }

    fun clearError() {
        _errorMessage.value = null
    }
}