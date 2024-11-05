package com.example.mydrafts.core

import com.example.mydrafts.Painting
import com.example.mydrafts.R

val landscapes = listOf(
    Painting(1, "Sunset in Mountains", "Alexander Barbara", 2015, "Picturesque image of sunset in mountains", R.drawable.landscape1),
    Painting(2, "A River in the Forest", "Martines Karlos", 1999, "A calm river surrounded by forest", R.drawable.landscape2)
)

val portraits = listOf(
    Painting(3, "A Girl's Portrait", "Anna Jain", 2000, "A beautiful young girl", R.drawable.portrait1),
    Painting(4, "A Boy with Toy", "Jon Boy", 1998, "A little boy playing with his toy car", R.drawable.portrait2)
)

val abstractions = listOf(
    Painting(5, "Abstract 1", "Nataly Smith", 2010, "Interesting abstract composition", R.drawable.abstract1),
    Painting(6, "Abstract 2", "Peter Yan", 2009, "Bright colors and unusual shapes", R.drawable.abstract2)
)

fun findPaintingById(id: Int?): Painting? {
    return (landscapes + portraits + abstractions).find { it.id == id }
}