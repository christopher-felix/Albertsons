package com.example.albertsons.model

import com.example.albertsons.model.remote.AcronymService

object AcronymRepo {

    suspend fun searchForLongForms(search: String): List<String> {
        return AcronymService.getInstance()
            .getAcronymSearchResults(search = search)
            .body()
            ?.flatMap { item -> item.lfs.map { longForm -> longForm.lf } }
            ?: emptyList()
    }
}
