package com.example.albertsons.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.albertsons.model.AcronymRepo

class AcronymVMFactory(
    private val repo: AcronymRepo
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AcronymViewModel(repo) as T
    }
}