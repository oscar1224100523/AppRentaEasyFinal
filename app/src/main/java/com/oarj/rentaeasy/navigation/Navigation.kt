package com.oarj.rentaeasy.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.oarj.rentaeasy.screens.createproperty.CreatePropertyScreen
import com.oarj.rentaeasy.screens.favorites.FavoritesScreen
import com.oarj.rentaeasy.screens.home.HomeScreen
import com.oarj.rentaeasy.screens.login.LoginScreen
import com.oarj.rentaeasy.screens.profile.ProfileScreen
import com.oarj.rentaeasy.screens.propertydetail.PropertyDetailScreen
import com.oarj.rentaeasy.screens.register.RegisterScreen
import com.oarj.rentaeasy.viewmodels.AuthViewModel
import com.oarj.rentaeasy.viewmodels.FavoriteViewModel
import com.oarj.rentaeasy.viewmodels.PropertyViewModel

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Favorites : Screen("favorites")
    object Profile : Screen("profile")
    object CreateProperty : Screen("create_property")
    object PropertyDetail : Screen("property_detail/{propertyId}") {
        fun createRoute(propertyId: String) = "property_detail/$propertyId"
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel = viewModel(),
    propertyViewModel: PropertyViewModel = viewModel(),
    favoriteViewModel: FavoriteViewModel = viewModel()
) {
    val currentUser by authViewModel.currentUser.collectAsState()

    val startDestination = if (currentUser != null) {
        Screen.Home.route
    } else {
        Screen.Login.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                authViewModel = authViewModel,
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                propertyViewModel = propertyViewModel,
                favoriteViewModel = favoriteViewModel,
                authViewModel = authViewModel,
                onNavigateToPropertyDetail = { propertyId ->
                    navController.navigate(Screen.PropertyDetail.createRoute(propertyId))
                },
                onNavigateToCreateProperty = {
                    navController.navigate(Screen.CreateProperty.route)
                },
                onNavigateToFavorites = {
                    navController.navigate(Screen.Favorites.route)
                },
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }

        composable(Screen.Favorites.route) {
            FavoritesScreen(
                favoriteViewModel = favoriteViewModel,
                authViewModel = authViewModel,
                onNavigateToPropertyDetail = { propertyId ->
                    navController.navigate(Screen.PropertyDetail.createRoute(propertyId))
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route)
                },
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                authViewModel = authViewModel,
                propertyViewModel = propertyViewModel,
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route)
                },
                onNavigateToFavorites = {
                    navController.navigate(Screen.Favorites.route)
                },
                onNavigateToPropertyDetail = { propertyId ->
                    navController.navigate(Screen.PropertyDetail.createRoute(propertyId))
                }
            )
        }

        composable(Screen.CreateProperty.route) {
            CreatePropertyScreen(
                propertyViewModel = propertyViewModel,
                authViewModel = authViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Screen.PropertyDetail.route,
            arguments = listOf(
                navArgument("propertyId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val propertyId = backStackEntry.arguments?.getString("propertyId") ?: ""
            PropertyDetailScreen(
                propertyId = propertyId,
                propertyViewModel = propertyViewModel,
                favoriteViewModel = favoriteViewModel,
                authViewModel = authViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}