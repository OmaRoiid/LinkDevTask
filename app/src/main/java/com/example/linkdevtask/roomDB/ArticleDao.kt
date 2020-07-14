package com.example.linkdevtask.roomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.linkdevtask.model.Articles

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticlesIntoDatabase(mArticle: List<Articles>)


    @Query("select * from article_detail")
    fun getSavedArticlesFromDB() : List<Articles>
}