package dev.sincere.todoapp.core.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
    val id: Long,
    val title: String,
    val content: String,
    val completion: Boolean
): Parcelable
