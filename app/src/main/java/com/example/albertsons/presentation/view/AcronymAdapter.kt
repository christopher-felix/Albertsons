package com.example.albertsons.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.albertsons.databinding.AcronymItemBinding

class AcronymAdapter : RecyclerView.Adapter<AcronymAdapter.AcronymViewHolder>() {

    private val searchResults: MutableList<String> = mutableListOf()

    fun updateList(newList: List<String>) {
        val oldSize = searchResults.size
        searchResults.clear()
        notifyItemRangeRemoved(0, oldSize)
        searchResults.addAll(newList)
        notifyItemRangeInserted(0, newList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = AcronymItemBinding.inflate(inflater, parent, false)
        return AcronymViewHolder(listItemBinding)

    }

    override fun onBindViewHolder(holder: AcronymViewHolder, position: Int) {
        holder.bind(searchResults[position])
    }

    override fun getItemCount(): Int = searchResults.size

    class AcronymViewHolder(var binding: AcronymItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.textView.text = item
        }
    }


}

