package com.example.mydrafts

import abstractions
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import findPaintingById
import landscapes
import portraits

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "landscapes") {
        composable("landscapes") { PaintingListScreen(navController, landscapes) }
        composable("portraits") { PaintingListScreen(navController, portraits) }
        composable("abstractions") { PaintingListScreen(navController, abstractions) }
        composable(
            "paintingDetail/{paintingId}",
            arguments = listOf(navArgument("paintingId") { type = NavType.IntType })
        ) { backStackEntry ->
            val paintingId = backStackEntry.arguments?.getInt("paintingId")
            val painting = findPaintingById(paintingId)
            painting?.let { PaintingDetailScreen(it) }
        }
    }
}