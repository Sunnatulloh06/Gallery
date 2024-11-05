package com.example.mydrafts.core

sealed class Routes(val route: String) {
    object LANDSCAPES_SCREEN:Routes("LANDSCAPES_SCREEN")
    object PORTRAITS_SCREEN:Routes("PORTRAITS_SCREEN")
    object ABSTRACTIONS_SCREEN:Routes("ABSTRACTIONS_SCREEN")
    object PAINTING_DETAIL_SCREEN:Routes("PAINTING_DETAIL_SCREEN/{${Constants.paintingId}}")
}