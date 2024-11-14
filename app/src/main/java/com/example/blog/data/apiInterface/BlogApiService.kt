package com.example.blog.data.apiInterface

import com.example.blog.data.model.BlogPost
import retrofit2.http.GET
import retrofit2.http.Query

interface BlogApiService {
    @GET("wp-json/wp/v2/posts")
    suspend fun getBlogPosts(
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1
    ): List<BlogPost>
}
