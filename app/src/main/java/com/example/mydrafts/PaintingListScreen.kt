package com.example.mydrafts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun PaintingListScreen(navController: NavHostController, paintings: List<Painting>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(paintings) { painting ->
            PaintingItem(painting) {
                navController.navigate("paintingDetail/${painting.id}")
            }
        }
    }
}