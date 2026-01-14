package com.oarj.rentaeasy.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oarj.rentaeasy.components.BottomNavigationBar
import com.oarj.rentaeasy.components.PropertyCard
import com.oarj.rentaeasy.components.SearchBar
import com.oarj.rentaeasy.viewmodels.AuthViewModel
import com.oarj.rentaeasy.viewmodels.FavoriteViewModel
import com.oarj.rentaeasy.viewmodels.PropertyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    propertyViewModel: PropertyViewModel,
    favoriteViewModel: FavoriteViewModel,
    authViewModel: AuthViewModel,
    onNavigateToPropertyDetail: (String) -> Unit,
    onNavigateToCreateProperty: () -> Unit,
    onNavigateToFavorites: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    val properties by propertyViewModel.properties.collectAsState()
    val isLoading by propertyViewModel.isLoading.collectAsState()
    val searchQuery by propertyViewModel.searchQuery.collectAsState()
    val currentUser by authViewModel.currentUser.collectAsState()
    val favoriteIds by favoriteViewModel.favoriteIds.collectAsState()

    LaunchedEffect(currentUser) {
        currentUser?.let { user ->
            favoriteViewModel.loadFavorites(user.id)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("RentEasy") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            if (currentUser?.userType == "propietario") {
                FloatingActionButton(
                    onClick = onNavigateToCreateProperty,
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Crear publicación"
                    )
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "home",
                onNavigate = { route ->
                    when (route) {
                        "favorites" -> onNavigateToFavorites()
                        "profile" -> onNavigateToProfile()
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchBar(
                query = searchQuery,
                onQueryChange = { query ->
                    propertyViewModel.searchProperties(query)
                }
            )

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (properties.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (searchQuery.isEmpty())
                            "No hay propiedades disponibles"
                        else
                            "No se encontraron propiedades",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(properties) { property ->
                        PropertyCard(
                            property = property,
                            isFavorite = favoriteIds.contains(property.id),
                            onPropertyClick = {
                                onNavigateToPropertyDetail(property.id)
                            },
                            onFavoriteClick = {
                                currentUser?.let { user ->
                                    favoriteViewModel.toggleFavorite(user.id, property.id)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}