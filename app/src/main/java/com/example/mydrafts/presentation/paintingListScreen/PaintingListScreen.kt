package com.example.mydrafts.presentation.paintingListScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.mydrafts.presentation.components.PaintingItem
import com.example.mydrafts.core.Constants
import com.example.mydrafts.core.Routes
import com.example.mydrafts.core.model.Painting

@Composable
fun PaintingListScreen(navController: NavHostController, paintings: List<Painting>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(paintings) { painting ->
            PaintingItem(painting) {
                navController.navigate(
                    Routes.PAINTING_DETAIL_SCREEN.route.replace(
                        "{${Constants.paintingId}}",
                        "{${painting.id}}"
                    )
                )
            }
        }
    }
}