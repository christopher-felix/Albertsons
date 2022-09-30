package com.example.albertsons.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.albertsons.R
import com.example.albertsons.databinding.ActivityMainBinding
import com.example.albertsons.model.AcronymRepo
import com.example.albertsons.presentation.viewmodel.AcronymVMFactory
import com.example.albertsons.presentation.viewmodel.AcronymViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val acronymViewModel: AcronymViewModel by viewModels() { AcronymVMFactory(AcronymRepo) }
    private val theAdapter: AcronymAdapter by lazy { AcronymAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = acronymViewModel
        initObservers()
    }

    fun initObservers() {
        acronymViewModel.uiState.observe(this) { state ->
            binding.rvAcronym.apply {
                adapter = theAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
                theAdapter.updateList(state.meanings)
            }
        }
    }
}