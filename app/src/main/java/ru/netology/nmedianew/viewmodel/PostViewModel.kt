package ru.netology.nmedianew.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.nmedianew.repository.PostRepositoryInMemory

class PostViewModel : ViewModel() {

    private val repository = PostRepositoryInMemory()

    val post = repository.getPost()

    fun like() {
        repository.like()
    }
}