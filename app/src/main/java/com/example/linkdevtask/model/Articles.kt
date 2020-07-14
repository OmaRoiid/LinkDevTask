package com.example.linkdevtask.model
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import org.parceler.Parcel
import org.parceler.ParcelConstructor
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "article_detail")
@Parcel(Parcel.Serialization.BEAN)
data class  Articles @ParcelConstructor constructor (
 @PrimaryKey(autoGenerate = true)
 val mId:Int,
 @SerializedName("author")
 @NonNull
 @ColumnInfo(name = "author_name")
 val author:String,
 @SerializedName("title")
 @NonNull
 @ColumnInfo(name = "article_title")
 val title:String,
 @SerializedName("description")
 @NonNull
 @ColumnInfo(name = "article_description")
 val description:String,
 @SerializedName("url")
 @NonNull
 @ColumnInfo(name = "article_url")
 val url:String,
 @SerializedName("urlToImage")
 @NonNull
 @ColumnInfo(name = "article_image")
 val urlToImage:String,
 @SerializedName("publishedAt")
 @NonNull
 @ColumnInfo(name = "article_date")
 val publishedAt:String
 ) :Serializable