package com.example.blog.repository

import com.example.blog.apiInterface.BlogApiService
import com.example.blog.model.BlogPost
import com.example.blog.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BlogRepository @Inject constructor(private val blogApiService: BlogApiService) {

    fun getBlogPosts(): Flow<Result<List<BlogPost>>> = flow {
        try {
            val posts = blogApiService.getBlogPosts()
            emit(Result.Success(posts))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }.flowOn(Dispatchers.IO)
}
