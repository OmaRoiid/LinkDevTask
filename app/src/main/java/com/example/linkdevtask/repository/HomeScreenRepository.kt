package com.example.linkdevtask.repository

import com.example.linkdevtask.model.Articles
import com.example.linkdevtask.rest.ApiServices
import com.example.linkdevtask.rest.ApiUtil
import com.example.linkdevtask.rest.RetrofitClient

class HomeScreenRepository : BaseRepository() {
     suspend fun fetchArticles() :MutableList<Articles>?{
     val mRetrofit=RetrofitClient()
         val mergedList:List<Articles>?
             val mResponseOne = safeApiCall(
                 call={mRetrofit.getRetrofitClient.create(ApiServices::class.java)
                 .getResponseOne(ApiUtil.getSOURCE_ONE, ApiUtil.getAPI_KEY).await()},
                 errorMessage = "Error Fetching Articles"
             )
         val mResponseTwo = safeApiCall(
             call={mRetrofit.getRetrofitClient.create(ApiServices::class.java)
                 .getResponseTwo(ApiUtil.getSOURCE_Two,ApiUtil.getAPI_KEY).await()},
             errorMessage = "Error Fetching Articles"
         )
        val mFirstList=mResponseOne?.articles?.toMutableList()
         val mSecondList=mResponseTwo?.articles?.toMutableList()
          mergedList=mFirstList.orEmpty()+mSecondList.orEmpty()
             return mergedList.toMutableList()
         }

     }

