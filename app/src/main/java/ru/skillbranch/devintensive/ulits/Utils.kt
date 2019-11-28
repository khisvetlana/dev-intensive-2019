package ru.skillbranch.devintensive.ulits

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        // TODO: Fix Me
        val parts : List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return Pair(firstName, lastName)
    }

    fun transliteration(payload: String, divider: String = " "): String {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates
    }

}