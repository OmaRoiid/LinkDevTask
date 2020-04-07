package com.example.linkdevtask.util

import java.text.SimpleDateFormat
import java.util.*

object DateFormatting {
    fun reFormat(strCurrentDate :String) :String{
        var format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("GMT")
        val newDate = format.parse(strCurrentDate)
        format = SimpleDateFormat("MMM dd,yyyy",Locale.getDefault())
        val date = format.format(newDate)
        return date
    }

}