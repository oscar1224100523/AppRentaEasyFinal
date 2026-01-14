package com.oarj.rentaeasy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.oarj.rentaeasy.navigation.NavigationGraph
import com.oarj.rentaeasy.ui.theme.RentaEasyTheme
import com.oarj.rentaeasy.viewmodels.AuthViewModel
import com.oarj.rentaeasy.viewmodels.FavoriteViewModel
import com.oarj.rentaeasy.viewmodels.PropertyViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RentaEasyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val authViewModel: AuthViewModel = viewModel()
                    val propertyViewModel: PropertyViewModel = viewModel()
                    val favoriteViewModel: FavoriteViewModel = viewModel()

                    NavigationGraph(
                        navController = navController,
                        authViewModel = authViewModel,
                        propertyViewModel = propertyViewModel,
                        favoriteViewModel = favoriteViewModel
                    )
                }
            }
        }
    }
}