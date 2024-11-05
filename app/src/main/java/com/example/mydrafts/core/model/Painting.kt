package com.example.mydrafts.core.model

data class Painting(
    val id: Int,
    val title: String,
    val author: String,
    val year: Int,
    val description: String,
    val imageResId: Int
)