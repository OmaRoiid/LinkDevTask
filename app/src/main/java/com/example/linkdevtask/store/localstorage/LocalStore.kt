package com.example.linkdevtask.store.localstorage

import com.example.linkdevtask.listeners.DatabaseCallBack
import com.example.linkdevtask.model.Articles
import com.example.linkdevtask.roomDB.ArticleDao
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LocalStore(private val mArticleDao: ArticleDao) {
    private   var articleDao:ArticleDao =mArticleDao

     suspend fun insertToDB(articleDetail: List<Articles>)  {
                 articleDao.insertArticlesIntoDatabase(articleDetail)
         }
    suspend fun fetchArticlesFromDB(mDatabaseCallBack: DatabaseCallBack)
    {
        mDatabaseCallBack.onComplete(articleDao.getSavedArticlesFromDB())
    }
}