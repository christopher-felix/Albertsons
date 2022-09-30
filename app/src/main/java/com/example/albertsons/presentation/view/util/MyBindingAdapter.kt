package com.example.albertsons.presentation.view.util

import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.databinding.BindingAdapter

interface SearchViewSubmitListener {
    fun submit(shortForm: String)
}

object MyBindingAdapter {
    @JvmStatic
    @BindingAdapter(value = ["bind:submitListener"])
    fun SearchView.setSearchViewListener(submitListener: SearchViewSubmitListener) {
        setOnQueryTextListener(
            object : OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    submitListener.submit(query)
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return true
                }
            }
        )
    }

}