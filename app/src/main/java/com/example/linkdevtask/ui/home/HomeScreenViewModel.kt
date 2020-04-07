package com.example.linkdevtask.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.linkdevtask.model.Articles
import com.example.linkdevtask.repository.HomeScreenRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HomeScreenViewModel(application: Application, mHomeScreenRepository: HomeScreenRepository) :
    AndroidViewModel(application) {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    private var homeScreenRepository = mHomeScreenRepository
    val articlesLiveData = MutableLiveData<MutableList<Articles>>()
    fun getArticlesFromServer(): MutableLiveData<MutableList<Articles>> {
        scope.launch {
            val articles = homeScreenRepository.fetchArticles()
            articlesLiveData.postValue(articles)
        }
        return articlesLiveData
    }

    private fun cancelAllRequests() = coroutineContext.cancel()

    override fun onCleared() {
        cancelAllRequests()
        super.onCleared()
    }
}