package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import  java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 60 * HOUR

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = java.text.SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND) : Date{
    var time = this.time

    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    return "только что"
    //TODO("Not implemented") // To change body of created functions use File | Settings | FileTemplates
}