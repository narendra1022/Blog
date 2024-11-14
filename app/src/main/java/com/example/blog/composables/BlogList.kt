package com.example.blog.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.blog.data.model.BlogPost
import com.example.blog.utils.Result
import com.example.blog.viewmodel.BlogViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun BlogList(posts: List<BlogPost>, onItemClick: (BlogPost) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(posts) { blogPost ->
            BlogItem(blogPost, onItemClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogListScreen(navController: NavController, viewModel: BlogViewModel = hiltViewModel()) {
    val blogPosts by viewModel.blogPosts.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Blogs") }) }
    ) { paddingValues ->
        // Apply content padding to the inner content
        Box(modifier = Modifier.padding(paddingValues)) {
            when (blogPosts) {
                is Result.Loading -> LoadingIndicator()
                is Result.Error -> ErrorMessage((blogPosts as Result.Error).exception)
                is Result.Success -> BlogList((blogPosts as Result.Success).data) { blogPost ->
                    // Navigate to blog content screen
                    val encodedBlogUrl =
                        URLEncoder.encode(blogPost.link, StandardCharsets.UTF_8.toString())
                    navController.navigate("blog/$encodedBlogUrl")
                }
            }
        }
    }
}

@Composable
fun ErrorMessage(exception: Throwable) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "Error: ${exception.localizedMessage}",
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
