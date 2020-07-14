package com.example.linkdevtask.roomDB

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.linkdevtask.model.Articles
import com.example.linkdevtask.ui.home.HomeScreenFragment

@Database(entities = [Articles::class],version = 1)
abstract class ArticleDataBase : RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao
    companion object {
        private var INSTANCE: ArticleDataBase? = null
        fun getDataBaseInstance(context: Application): ArticleDataBase {
            if (INSTANCE == null) {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ArticleDataBase::class.java,
                        "articles_database"
                    ).fallbackToDestructiveMigration().build()

                  INSTANCE=instance
                }
            }
            return INSTANCE!!
        }
    }
}