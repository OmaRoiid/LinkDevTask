package com.example.linkdevtask.store.repository

import androidx.lifecycle.LiveData
import com.example.linkdevtask.model.Articles
import com.example.linkdevtask.roomDB.ArticleDao
import com.example.linkdevtask.store.localstorage.LocalStore

import com.example.linkdevtask.store.webservice.WebServiceStore

class HomeScreenRepository( private val mWebServiceStore: WebServiceStore,private val mLocalStore: LocalStore,private val mOnline:Boolean)  {
    private val webServiceStore = mWebServiceStore
    private val localStore=mLocalStore
     suspend fun fetchArticles() :List<Articles>{
             return if(mOnline) {
                val mArticlesList= webServiceStore.fetchArticlesFromApi()
                 localStore.insertToDB(mArticlesList)
                 mArticlesList
             } else{
                 localStore.fetchArticlesFromDB()
             }
         }
     }

