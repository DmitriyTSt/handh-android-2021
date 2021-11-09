package ru.dmitriyt.lesson_7.data.model

import com.google.gson.annotations.SerializedName

data class Bridge(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("name_eng") val nameEng: String?,
    @SerializedName("description") val description: String?,
)