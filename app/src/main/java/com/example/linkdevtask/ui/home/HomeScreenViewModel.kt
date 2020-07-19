package com.example.linkdevtask.ui.home
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.linkdevtask.model.Articles
import com.example.linkdevtask.roomDB.ArticleDataBase
import com.example.linkdevtask.store.localstorage.LocalStore
import com.example.linkdevtask.store.repository.HomeScreenRepository
import com.example.linkdevtask.store.webservice.WebServiceStore
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HomeScreenViewModel(application: Application, mIsOnline:Boolean) :
    AndroidViewModel(application) {
    private val mHomeScreenRepository: HomeScreenRepository
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    init {
         val mWebServiceStore = WebServiceStore()
         val articleDao= ArticleDataBase.getDataBaseInstance(application).getArticleDao()
         val mLocalStore =LocalStore(articleDao)
        mHomeScreenRepository=  HomeScreenRepository(mWebServiceStore,mLocalStore,mIsOnline)
    }

    val articlesLiveData = MutableLiveData<List<Articles>>()
       fun getArticlesFromServer() {
        scope.launch {
            val articles = mHomeScreenRepository.fetchArticles()
            articlesLiveData.postValue(articles)
        }
    }

    private fun cancelAllRequests() = coroutineContext.cancel()
    override fun onCleared() {
        cancelAllRequests()
        super.onCleared()
    }
}