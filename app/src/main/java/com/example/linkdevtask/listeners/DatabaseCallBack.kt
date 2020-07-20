package com.example.linkdevtask.listeners

import com.example.linkdevtask.model.Articles

interface DatabaseCallBack {
    fun onComplete (mEmittedArticlesList :List<Articles>)
}
