package com.example.blog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blog.data.model.BlogPost
import com.example.blog.data.repository.BlogRepository
import com.example.blog.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogViewModel @Inject constructor(private val repository: BlogRepository) : ViewModel() {

    private val _blogPosts =
        MutableStateFlow<Result<List<BlogPost>>>(Result.Loading)
    val blogPosts: StateFlow<Result<List<BlogPost>>> = _blogPosts

    init {
        fetchBlogs()
    }

    private fun fetchBlogs() {
        viewModelScope.launch {
            repository.getBlogPosts()
                .onStart { _blogPosts.value = Result.Loading }
                .catch { exception ->
                    _blogPosts.value = Result.Error(exception)
                }
                .collect { result -> _blogPosts.value = result }
        }
    }
}
