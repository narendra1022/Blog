package com.example.blog.navigation

import android.webkit.WebView
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.blog.composables.BlogContentScreen
import com.example.blog.composables.BlogListScreen
import com.example.blog.data.model.BlogPost
import com.example.blog.viewmodel.BlogViewModel
import com.example.blog.utils.Result
import java.net.URLDecoder
import java.net.URLEncoder
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


