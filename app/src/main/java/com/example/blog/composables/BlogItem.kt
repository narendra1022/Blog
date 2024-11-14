package com.example.blog.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.blog.data.model.BlogPost

@Composable
fun BlogItem(blogPost: BlogPost, onClick: (BlogPost) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(blogPost) }
            .clip(RoundedCornerShape(5.dp))
            .animateContentSize(), // Animated card resizing
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = blogPost.title.rendered,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black // Set title text color to black
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}