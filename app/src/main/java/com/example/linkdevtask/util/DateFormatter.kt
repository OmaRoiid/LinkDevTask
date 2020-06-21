package com.example.linkdevtask.util

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {
    fun format(strCurrentDate :String) :String{
        var format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("GMT")
        val newDate = format.parse(strCurrentDate)
        format = SimpleDateFormat("MMM dd,yyyy",Locale.getDefault())
        val mDateFormatted = format.format(newDate)
        return mDateFormatted
    }

}