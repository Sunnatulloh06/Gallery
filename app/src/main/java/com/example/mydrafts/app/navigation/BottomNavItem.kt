package com.example.mydrafts.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mydrafts.core.Routes

val items = listOf(
    BottomNavItem("Landscapes", Routes.LANDSCAPES_SCREEN.route, Icons.Default.Home),
    BottomNavItem("Portraits", Routes.PORTRAITS_SCREEN.route, Icons.Default.Person),
    BottomNavItem("Abstractions", Routes.ABSTRACTIONS_SCREEN.route, Icons.Default.Edit)
)

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector
)

