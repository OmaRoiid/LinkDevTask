package com.example.linkdevtask.listeners

import com.example.linkdevtask.model.Articles

interface WebServiceCallBack {
    fun onComplete (mEmittedArticlesList :List<Articles>)
}