package com.example.linkdevtask.rest

object ApiUtil {
    private const val BASE_URL="https://newsapi.org/v1/"
    val getBASE_URL:String get()=BASE_URL
    private const val API_KEY="43fb4bdc8c1e40bcb65922eb346e0264"
    val getAPI_KEY:String get()=API_KEY
    private const val SOURCE_ONE="the-next-web"
    val getSOURCE_ONE:String get()=SOURCE_ONE
    private const val SOURCE_TWO="associated-press"
    val getSOURCE_Two:String get()=SOURCE_TWO
}