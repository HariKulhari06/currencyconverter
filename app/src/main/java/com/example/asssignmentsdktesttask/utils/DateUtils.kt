package com.example.asssignmentsdktesttask.utils

import java.text.SimpleDateFormat
import java.util.*


fun formatUixTime(timeStamp: Long): String {
    val date = Date(timeStamp.times(1000))
    val sdf = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("GMT-4")
    return sdf.format(date)
}