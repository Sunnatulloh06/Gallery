package com.example.mydrafts.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mydrafts.presentation.paintingDetailScreen.PaintingDetailScreen
import com.example.mydrafts.presentation.paintingListScreen.PaintingListScreen
import com.example.mydrafts.core.Constants
import com.example.mydrafts.core.Routes
import com.example.mydrafts.core.abstractions
import com.example.mydrafts.core.findPaintingById
import com.example.mydrafts.core.landscapes
import com.example.mydrafts.core.portraits

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.LANDSCAPES_SCREEN.route
    ) {
        composable(Routes.LANDSCAPES_SCREEN.route) {
            PaintingListScreen(navController, landscapes)
        }
        composable(Routes.PORTRAITS_SCREEN.route) {
            PaintingListScreen(navController, portraits)
        }
        composable(Routes.ABSTRACTIONS_SCREEN.route) {
            PaintingListScreen(
                navController,
                abstractions
            )
        }
        composable(
            Routes.PAINTING_DETAIL_SCREEN.route,
            arguments = listOf(navArgument(Constants.paintingId) { type = NavType.IntType })
        ) { backStackEntry ->
            val paintingId = backStackEntry.arguments?.getInt(Constants.paintingId)
            val painting = findPaintingById(paintingId)
            painting?.let {
                PaintingDetailScreen(it)
            }
        }
    }
}