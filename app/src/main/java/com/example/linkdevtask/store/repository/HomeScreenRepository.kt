package com.example.linkdevtask.store.repository

import com.example.linkdevtask.listeners.DatabaseCallBack
import com.example.linkdevtask.listeners.WebServiceCallBack
import com.example.linkdevtask.model.Articles
import com.example.linkdevtask.store.localstorage.LocalStore
import com.example.linkdevtask.store.webservice.WebServiceStore

class HomeScreenRepository( private val mWebServiceStore: WebServiceStore,private val mLocalStore: LocalStore,private val mOnline:Boolean)  {
    private val webServiceStore = mWebServiceStore
    private val localStore=mLocalStore
    private lateinit var mArticlesList :List<Articles>
     suspend fun fetchArticles() :List<Articles> {
             return if(mOnline) {
               webServiceStore.fetchArticlesFromApi(object :WebServiceCallBack{
                   override fun onComplete(mEmittedArticlesList: List<Articles>){
                       mArticlesList=mEmittedArticlesList
                   }
               })
                 localStore.insertToDB(mArticlesList)
                 mArticlesList
             } else{
                 localStore.fetchArticlesFromDB(object :DatabaseCallBack{
                     override fun onComplete(mEmittedArticlesList: List<Articles>) {
                      mArticlesList=mEmittedArticlesList
                     }
                 })
                 mArticlesList
             }
         }
     }

