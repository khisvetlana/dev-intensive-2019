package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Bender.Question.NAME) {
    fun askQuestion(): String = when(question){
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String) : Pair<String, Triple<Int, Int, Int>>{
       var validationText = ""
        var validationResult = true
        if (answer != null && answer.isNotEmpty()) {
            when (question) {
                Question.NAME -> {
                    validationResult = answer[0].isUpperCase()
                    if (!validationResult)
                        validationText = "Имя должно начинаться с заглавной буквы"
                }
                Question.PROFESSION -> {
                    validationResult = answer[0].isLowerCase()
                    if (!validationResult)
                        validationText = "Профессия должна начинаться со строчной буквы"
                }
                Question.MATERIAL -> {
                    validationResult = onlyLetters(answer)
                    if (!validationResult)
                        validationText = "Материал не должен содержать цифр"
                }
                Question.BDAY -> {
                    validationResult = onlyNumbers(answer)
                    if (!validationResult)
                        validationText = "Год моего рождения должен содержать только цифры"
                }
                Question.SERIAL -> {
                    validationResult = correctSerialNumber(answer)
                    if (!validationResult)
                        validationText = "Серийный номер содержит только цифры, и их 7"
                }
                Question.IDLE -> {
                    validationText = ""
                }
            }
        }

        return if (question == Bender.Question.IDLE || question.answers.contains(answer)) {
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        } else {
            if(validationResult) {
                if (answer.isNotEmpty()) {
                    status = status.nextStatus()
                }

                if (status == Status.NORMAL && answer.isNotEmpty()){
                    question = Bender.Question.NAME
                    "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
                } else {
                    "Это неправильный ответ\n${question.question}" to status.color
                }
            } else {
                (validationText + "\n${question.question}") to status.color
            }
        }
    }

    fun onlyLetters(answer: String): Boolean{
        val regex = Regex(pattern = """[A-Za-zА-Яа-я]+""")
        return regex.matches(answer)
    }

    fun onlyNumbers(answer: String): Boolean{
        val regex = Regex(pattern = """[0-9]+""")
        return regex.matches(answer)
    }

    fun correctSerialNumber(answer: String): Boolean{
        val regex = Regex(pattern = "[0-9]{7}")
        return regex.matches(answer)
    }

    enum class Status(val color:Triple<Int, Int, Int> ) {
        NORMAL(Triple(255, 255, 255)) ,
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0)) ;

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex){
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>){
        NAME("Как меня зовут?", listOf("Бендер", "Bender")) {
            override fun nextQuestion(): Question  = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")){
            override fun nextQuestion(): Question  = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")){
            override fun nextQuestion(): Question  = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Question  = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question  = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question  = IDLE
        };

        abstract fun nextQuestion() : Question
    }
}