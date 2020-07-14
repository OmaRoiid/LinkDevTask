package com.example.linkdevtask.store.localstorage

import androidx.lifecycle.LiveData
import com.example.linkdevtask.model.Articles
import com.example.linkdevtask.roomDB.ArticleDao

class LocalStore(private val mArticleDao: ArticleDao) {
    private  var articleDao=mArticleDao
    private  val allArticles =articleDao.getSavedArticlesFromDB()

     suspend fun insertToDB(articleDetail: List<Articles>)  {
         articleDao.insertArticlesIntoDatabase(articleDetail)
            }
    fun fetchArticlesFromDB() : List<Articles>
    {
        return allArticles
    }
}