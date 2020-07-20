package com.example.linkdevtask.store.webservice

import com.example.linkdevtask.listeners.WebServiceCallBack
import com.example.linkdevtask.model.Articles
import com.example.linkdevtask.rest.ApiServices
import com.example.linkdevtask.rest.ApiUtil
import com.example.linkdevtask.rest.RetrofitClient

class WebServiceStore : BaseApiCall(){

    suspend fun fetchArticlesFromApi(mWebServiceCallBack: WebServiceCallBack){
        val mRetrofit= RetrofitClient()
        val mergedList:List<Articles>?
        val mResponseOne = safeApiCall(
            call={mRetrofit.getRetrofitClient.create(ApiServices::class.java)
                .getResponseOneAsync(ApiUtil.getSOURCE_ONE, ApiUtil.getAPI_KEY).await()},
            errorMessage = "Error Fetching Articles"
        )
        val mResponseTwo = safeApiCall(
            call={mRetrofit.getRetrofitClient.create(ApiServices::class.java)
                .getResponseTwoAsync(ApiUtil.getSOURCE_Two, ApiUtil.getAPI_KEY).await()},
            errorMessage = "Error Fetching Articles"
        )
        val mFirstList=mResponseOne?.articles
        val mSecondList=mResponseTwo?.articles
        mergedList=mFirstList!!+ mSecondList!!
        mWebServiceCallBack.onComplete(mergedList)
    }
}