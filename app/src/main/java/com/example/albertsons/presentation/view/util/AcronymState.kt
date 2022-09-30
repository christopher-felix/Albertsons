package com.example.albertsons.presentation.view.util

data class AcronymState(
    val meanings: List<String> = listOf(),
    val error: String = "",
    val loading: Boolean = false
)
