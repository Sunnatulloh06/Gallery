package com.example.mydrafts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PaintingDetailScreen(painting: Painting) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = painting.imageResId),
            contentDescription = painting.description,
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Title: ${painting.title}", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text(text = "Author: ${painting.author}", fontSize = 20.sp)
        Text(text = "Year: ${painting.year}", fontSize = 20.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = painting.description, fontSize = 16.sp, textAlign = TextAlign.Justify)
    }
}
