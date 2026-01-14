package com.oarj.rentaeasy.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oarj.rentaeasy.models.Property
import com.oarj.rentaeasy.repository.PropertyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PropertyViewModel : ViewModel() {
    private val repository = PropertyRepository()

    private val _properties = MutableStateFlow<List<Property>>(emptyList())
    val properties: StateFlow<List<Property>> = _properties

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        loadProperties()
    }

    fun loadProperties() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.getAllProperties()
            result.onSuccess { properties ->
                _properties.value = properties
            }.onFailure { exception ->
                _errorMessage.value = exception.message
            }
            _isLoading.value = false
        }
    }

    fun searchProperties(query: String) {
        _searchQuery.value = query
        if (query.isEmpty()) {
            loadProperties()
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.searchProperties(query)
            result.onSuccess { properties ->
                _properties.value = properties
            }.onFailure { exception ->
                _errorMessage.value = exception.message
            }
            _isLoading.value = false
        }
    }

    fun createProperty(
        property: Property,
        imageUris: List<Uri>,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.createProperty(property, imageUris)
            result.onSuccess {
                loadProperties()
                onSuccess()
            }.onFailure { exception ->
                _errorMessage.value = exception.message
            }
            _isLoading.value = false
        }
    }

    fun deleteProperty(propertyId: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.deleteProperty(propertyId)
            result.onSuccess {
                loadProperties()
                onSuccess()
            }.onFailure { exception ->
                _errorMessage.value = exception.message
            }
            _isLoading.value = false
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}