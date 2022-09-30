package com.example.albertsons.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albertsons.model.AcronymRepo
import com.example.albertsons.presentation.view.util.AcronymState
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class AcronymViewModel(private val repo: AcronymRepo) : ViewModel() {

    private val _uiState = MutableLiveData(AcronymState())
    val uiState: LiveData<AcronymState> get() = _uiState

    fun searchAcronym(shortForm: String) {
        viewModelScope.launch {
            try {
                val response = repo.searchForLongForms(search = shortForm)
                _uiState.value = _uiState.value?.copy(meanings = response, loading = false)
            } catch (ex: IOException) {
                _uiState.value = _uiState.value?.copy(error = "IOException", loading = false)
            } catch (ex: HttpException) {
                _uiState.value = _uiState.value?.copy(
                    error = "HttpException: code is ${ex.code()}",
                    loading = false
                )
            } catch (ex: Exception) {
                _uiState.value = _uiState.value?.copy(error = "OTHER Exception", loading = false)
            }
        }
    }
}
