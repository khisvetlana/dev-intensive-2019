package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.lang.Math.abs
import  java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value:Int): String {
        val preLastDigit = value % 100 / 10
        if (preLastDigit == 1) {
            when (this) {
                SECOND -> return value.toString() + " секунд"
                MINUTE -> return value.toString() + " минут"
                HOUR -> return value.toString() + " часов"
                DAY -> return value.toString() + " дней"
            }
        }

        when (value % 10) {
            1 ->
                when (this) {
                    SECOND -> return value.toString() + " секунду"
                    MINUTE -> return value.toString() + " минуту"
                    HOUR -> return value.toString() + " час"
                    DAY -> return value.toString() + " день"
                }
            2, 3, 4 ->
                when (this) {
                    SECOND -> return value.toString() + " секунды"
                    MINUTE -> return value.toString() + " минуты"
                    HOUR -> return value.toString() + " часа"
                    DAY -> return value.toString() + " дня"
                }
            else ->
                when (this) {
                    SECOND -> return value.toString() + " секунд"
                    MINUTE -> return value.toString() + " минут"
                    HOUR -> return value.toString() + " часов"
                    DAY -> return value.toString() + " дней"
                }
        }
    }
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
    val count = date.time - this.time
    val future: Boolean = count < 0;

    when (abs(count)) {
        in 0..1*SECOND -> return "только что"
        in 1*SECOND..45*SECOND ->
            return if (future) "через несколько секунд" else "несколько секунд назад"
        in 45*SECOND..75*SECOND ->
            return if (future) "через минуту" else "минуту назад"
        in 75*SECOND..45 * MINUTE ->
            return (if (future) "через " else "") +
                    TimeUnits.MINUTE.plural((abs(count)/ MINUTE).toInt())  +
                   if (!future) " назад" else ""
        in 45 * SECOND..75 * MINUTE ->
            return if (future) "через час" else "час назад"
        in 75 * SECOND..22 * HOUR ->
            return (if (future) "через " else "") +
                    TimeUnits.HOUR.plural((abs(count)/ HOUR).toInt()) +
                   if (!future) " назад" else ""
        in 22 * HOUR..26 * HOUR ->
            return if (future) "через день" else "день назад"
        in 26 * HOUR..360 * DAY ->
            return (if (future) "через " else "") +
                    TimeUnits.DAY.plural((abs(count)/ DAY).toInt()) +
                   if (!future) " назад" else ""
        else -> if (future) return "более чем через год"
                else return "более года назад"
    }
}

//    fun getCorrectTimeUnit(count: Long, timeUnit: TimeUnits): String {
//        val preLastDigit = count % 100 / 10
//        if (preLastDigit == 1L) {
//            when (timeUnit) {
//              TimeUnits.SECOND -> return "секунд"
//              TimeUnits.MINUTE -> return "минут"
//              TimeUnits.HOUR -> return "часов"
//              TimeUnits.DAY -> return "дней"
//            }
//        }
//
//        when (count % 10L) {
//            1L ->
//                when (timeUnit) {
//                    TimeUnits.SECOND -> return "секунда"
//                    TimeUnits.MINUTE -> return "минута"
//                    TimeUnits.HOUR -> return "час"
//                    TimeUnits.DAY -> return "день"
//                }
//            2L, 3L, 4L ->
//                when (timeUnit) {
//                    TimeUnits.SECOND -> return "секунды"
//                    TimeUnits.MINUTE -> return "минуты"
//                    TimeUnits.HOUR -> return "часа"
//                    TimeUnits.DAY -> return "дня"
//                }
//            else ->
//                when (timeUnit) {
//                    TimeUnits.SECOND -> return "секунд"
//                    TimeUnits.MINUTE -> return "минут"
//                    TimeUnits.HOUR -> return "часов"
//                    TimeUnits.DAY -> return "дней"
//                }
//        }
//    }