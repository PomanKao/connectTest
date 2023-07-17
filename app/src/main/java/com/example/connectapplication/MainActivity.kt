package com.example.connectapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.connectapplication.databinding.ActivityMainBinding
import com.example.connectapplication.viewmodel.ConnectViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val connectViewModel: ConnectViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRequest.setOnClickListener {
            connectViewModel.httpGetPosts()
        }
    }
}

