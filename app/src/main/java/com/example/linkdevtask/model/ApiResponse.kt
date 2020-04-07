package com.example.linkdevtask.model
import com.google.gson.annotations.SerializedName

data class ApiResponse (
    @SerializedName("status")
    var status:String,
    @SerializedName("source")
    var source:String,
    @SerializedName("sortBy")
    var sortBy:String,
    @SerializedName("articles")
    var articles: List<Articles>

)
