package com.example.linkdevtask.store.repository

import com.example.linkdevtask.model.Articles

import com.example.linkdevtask.store.webservice.WebServiceStore

class HomeScreenRepository( private val mWebServiceStore: WebServiceStore)  {
    private var webServiceStore = mWebServiceStore
     suspend fun fetchArticles() :List<Articles>?{
             return webServiceStore.fetchArticlesFromApi()
         }

     }

