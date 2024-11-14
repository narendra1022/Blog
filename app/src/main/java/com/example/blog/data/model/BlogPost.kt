package com.example.blog.data.model

data class BlogPost(
    val id: Int,
    val title: Title,
    val content: Content,
    val link: String
)

data class Title(val rendered: String)
data class Content(val rendered: String)
