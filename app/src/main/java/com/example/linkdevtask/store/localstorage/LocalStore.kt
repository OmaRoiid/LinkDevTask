package com.example.linkdevtask.store.localstorage

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.linkdevtask.model.Articles
import com.example.linkdevtask.roomDB.ArticleDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalStore(private val mArticleDao: ArticleDao) {
    private   var articleDao:ArticleDao =mArticleDao
     suspend fun insertToDB(articleDetail: List<Articles>)  {
                 articleDao.insertArticlesIntoDatabase(articleDetail)
         }
      fun fetchArticlesFromDB() : List<Articles>
    {
        lateinit var allArticles : List<Articles>
        GlobalScope.launch(Dispatchers.IO) {
             allArticles=articleDao.getSavedArticlesFromDB()
        }
        return allArticles
    }
}