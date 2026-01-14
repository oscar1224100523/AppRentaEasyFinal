package com.oarj.rentaeasy.screens.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oarj.rentaeasy.components.BottomNavigationBar
import com.oarj.rentaeasy.components.PropertyCard
import com.oarj.rentaeasy.viewmodels.AuthViewModel
import com.oarj.rentaeasy.viewmodels.FavoriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    favoriteViewModel: FavoriteViewModel,
    authViewModel: AuthViewModel,
    onNavigateToPropertyDetail: (String) -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    val favoriteProperties by favoriteViewModel.favoriteProperties.collectAsState()
    val isLoading by favoriteViewModel.isLoading.collectAsState()
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
                title = { Text("Favoritos") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "favorites",
                onNavigate = { route ->
                    when (route) {
                        "home" -> onNavigateToHome()
                        "profile" -> onNavigateToProfile()
                    }
                }
            )
        }
    ) { paddingValues ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (favoriteProperties.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No tienes favoritos",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Agrega propiedades a favoritos desde la pantalla de inicio",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(favoriteProperties) { property ->
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