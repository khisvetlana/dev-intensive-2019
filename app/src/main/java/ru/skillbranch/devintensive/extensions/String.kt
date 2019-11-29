package ru.skillbranch.devintensive.extensions

fun String.truncate(maxLength: Int = 16): String {
    var result: String = this

    if (this.trim().length > maxLength) {
        result = this.substring(0, maxLength)
        if (result.get(result.lastIndex).equals(" ")) result = result.dropLast( 1)
        result += "..."
    }

    return result.trim()
}

fun String.stripHtml(): String {
    var result: String = this

//    html теги
    result = Regex("<(.+?)>").replace(result, "")
//    html escape последовательности ("& < > ' ""),
    result = Regex("&|'|\"").replace(result, "")
//    пустые символы (пробелы) между словами если их больше 1
    result = Regex("\\s+").replace(result, " ")
    return result
}