package com.example.mydrafts

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import java.time.Year

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

data class Painting(
    val id: Int,
    val title: String,
    val author: String,
    val year: Int,
    val description: String,
    val imageResId: Int
)

data class BottomNavItem(val name: String, val route: String, val icon: ImageVector)

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

@Composable
fun BottomNavigationBar(navController: NavController, items: List<BottomNavItem>) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.name) },
                label = { Text(text = item.name) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

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

@Composable
fun PaintingItem(painting: Painting, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onClick() }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = painting.imageResId),
                contentDescription = painting.title,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = painting.title, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Text(text = "Author: ${painting.author}", fontSize = 22.sp)
            }
        }
    }
}

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

fun findPaintingById(id: Int?): Painting? {
    return (landscapes + portraits + abstractions).find { it.id == id }
}