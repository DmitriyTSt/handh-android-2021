package ru.handh.lesson_4.model

import ru.handh.lesson_4.AdapterElement

data class User(
    val name: String,
    val surname: String,
    val birthday: String,
) : AdapterElement