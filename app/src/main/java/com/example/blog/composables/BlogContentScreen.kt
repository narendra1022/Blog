package com.example.blog.composables

import android.webkit.WebView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogContentScreen(blogUrl: String) {
    // State to manage loading status
    var isLoading by remember { mutableStateOf(true) }

    // Use Scaffold for the top bar and padding
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Blog Content") }
            )
        }
    ) { paddingValues ->

        // Apply padding to WebView content
        Box(modifier = Modifier.padding(paddingValues)) {
            // Conditionally show progress bar or WebView
            if (isLoading) {
                LoadingIndicator()
            } else {
                // Decode URL once before loading
                val decodedUrl = URLDecoder.decode(blogUrl, StandardCharsets.UTF_8.toString())
                WebViewComposable(
                    url = decodedUrl
                )
            }
        }
    }
}

@Composable
fun WebViewComposable(url: String) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            settings.javaScriptEnabled = true
            loadUrl(url)
        }
    }, modifier = Modifier.fillMaxSize())
}

@Composable
fun LoadingIndicator() {
    // A simple loading indicator centered in the screen
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
