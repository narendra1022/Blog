package com.example.blog.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.blog.composables.BlogContentScreen
import com.example.blog.composables.BlogListScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "blogs") {
        composable("blogs") {
            BlogListScreen(navController)
        }
        composable(
            route = "blog/{blogUrl}",
            arguments = listOf(navArgument("blogUrl") { type = NavType.StringType })
        ) { backStackEntry ->
            // Safely extract and decode the blogUrl
            val blogUrl = backStackEntry.arguments?.getString("blogUrl")
            blogUrl?.let {
                // Decode the URL before passing it to the BlogContentScreen
                val decodedUrl = URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
                BlogContentScreen(blogUrl = decodedUrl)
            }
        }
    }
}


