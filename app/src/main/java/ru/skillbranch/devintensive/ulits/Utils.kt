package ru.skillbranch.devintensive.ulits

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts : List<String>? = fullName?.split(" ")

        var firstName = parts?.getOrNull(0)
        if (firstName != null && firstName.trim().length == 0)  firstName = null

        var lastName = parts?.getOrNull(1)
        if (lastName != null && lastName.trim().length == 0)  lastName = null

        return Pair(firstName, lastName)
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if (firstName?.trim()?.length ?: 0 == 0 &&
            lastName?.trim()?.length ?: 0 == 0)
            return null

        return (firstName?.get(0)?.toString()?.toUpperCase() ?: "") +
               (lastName?.get(0)?.toString()?.toUpperCase() ?: "")
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val parts : List<String> = payload.split(" ")
        var transliteralParts : MutableList<String> = mutableListOf()
        var result: String =""
        var transliteralWord: String

        for (i in parts.indices) {
            transliteralWord = ""
            for (letter in parts[i]){
                transliteralWord += getTransliterationLetter(letter)
            }
            transliteralParts.add(transliteralWord)
        }

        for (i in transliteralParts.indices) {
            result += transliteralParts[i] +
                      (if (i == transliteralParts.lastIndex) "" else divider)
        }

        return result
    }

    fun getTransliterationLetter(letter:   Char): String {
        return when(letter) {
            'а'-> "a"
            'А'-> "A"
            'б'-> "b"
            'Б'-> "B"
            'в'-> "v"
            'В'-> "V"
            'г'-> "g"
            'Г'-> "G"
            'д'-> "d"
            'Д'-> "D"
            'е'-> "e"
            'Е'-> "E"
            'ё'-> "e"
            'Ё'-> "E"
            'ж'-> "zh"
            'Ж'-> "Zh"
            'з'-> "z"
            'З'-> "Z"
            'и'-> "i"
            'И'-> "I"
            'й'-> "i"
            'Й'-> "I"
            'к'-> "k"
            'К'-> "K"
            'л'-> "l"
            'Л'-> "L"
            'м'-> "m"
            'М'-> "M"
            'н'-> "n"
            'Н'-> "N"
            'о'-> "o"
            'О'-> "O"
            'п'-> "p"
            'П'-> "P"
            'р'-> "r"
            'Р'-> "R"
            'с'-> "s"
            'С'-> "S"
            'т'-> "t"
            'Т'-> "T"
            'у'-> "u"
            'у'-> "U"
            'ф'-> "f"
            'Ф'-> "F"
            'х'-> "h"
            'Х'-> "H"
            'ц'-> "c"
            'Ц'-> "C"
            'ч'-> "ch"
            'Ч'-> "Ch"
            'ш'-> "sh"
            'Ш'-> "Sh"
            'щ'-> "sh"
            'Щ'-> "Sh"
            'ъ'-> ""
            'Ъ'-> ""
            'ы'-> "i"
            'Ы'-> "I"
            'ь'-> ""
            'Ь'-> ""
            'э'-> "e"
            'Э'-> "E"
            'ю'-> "yu"
            'Ю'-> "Уu"
            'я'-> "ya"
            'Я'-> "Уa"
            else -> letter.toString()
        }

    }
/*
    "а": "a",
    "б": "b",
    "в": "v",
    "г": "g",
    "д": "d",
    "е": "e",
    "ё": "e",
    "ж": "zh",
    "з": "z",
    "и": "i",
    "й": "i",
    "к": "k",
    "л": "l",
    "м": "m",
    "н": "n",
    "о": "o",
    "п": "p",
    "р": "r",
    "с": "s",
    "т": "t",
    "у": "u",
    "ф": "f",
    "х": "h",
    "ц": "c",
    "ч": "ch",
    "ш": "sh",
    "щ": "sh'",
    "ъ": "",
    "ы": "i",
    "ь": "",
    "э": "e",
    "ю": "yu",
    "я": "ya",*/
}