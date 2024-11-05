package com.example.mydrafts.ui.theme

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.mydrafts.BottomNavItem
import com.example.mydrafts.BottomNavigationBar
import com.example.mydrafts.NavigationHost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val items = listOf(
        BottomNavItem("Landscapes", "landscapes", Icons.Default.Home),
        BottomNavItem("Portraits", "portraits", Icons.Default.Person),
        BottomNavItem("Abstractions", "abstractions", Icons.Default.Edit)
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, items = items)
        }
    ) {
        NavigationHost(navController)
    }
}