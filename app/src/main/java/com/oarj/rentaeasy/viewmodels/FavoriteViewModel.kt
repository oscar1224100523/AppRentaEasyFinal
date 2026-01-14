package com.oarj.rentaeasy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oarj.rentaeasy.models.Property
import com.oarj.rentaeasy.repository.FavoriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {
    private val repository = FavoriteRepository()

    private val _favoriteProperties = MutableStateFlow<List<Property>>(emptyList())
    val favoriteProperties: StateFlow<List<Property>> = _favoriteProperties

    private val _favoriteIds = MutableStateFlow<Set<String>>(emptySet())
    val favoriteIds: StateFlow<Set<String>> = _favoriteIds

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadFavorites(userId: String) {
        viewModelScope.launch {
            _isLoading.value = true

            val idsResult = repository.getFavorites(userId)
            idsResult.onSuccess { ids ->
                _favoriteIds.value = ids.toSet()
            }

            val propertiesResult = repository.getFavoriteProperties(userId)
            propertiesResult.onSuccess { properties ->
                _favoriteProperties.value = properties
            }

            _isLoading.value = false
        }
    }

    fun toggleFavorite(userId: String, propertyId: String) {
        viewModelScope.launch {
            if (_favoriteIds.value.contains(propertyId)) {
                repository.removeFavorite(userId, propertyId)
                _favoriteIds.value = _favoriteIds.value - propertyId
            } else {
                repository.addFavorite(userId, propertyId)
                _favoriteIds.value = _favoriteIds.value + propertyId
            }
            loadFavorites(userId)
        }
    }

    fun isFavorite(propertyId: String): Boolean {
        return _favoriteIds.value.contains(propertyId)
    }
}