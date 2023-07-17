package com.example.connectapplication

import com.example.connectapplication.viewmodel.ConnectViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ConnectViewModel()
    }
}