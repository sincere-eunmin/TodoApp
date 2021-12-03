package dev.sincere.todoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import dev.sincere.todoapp.R
import dev.sincere.todoapp.databinding.ActivitySingleBinding

@AndroidEntryPoint
class SingleActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        setContentView(binding.root)
    }

    private fun bind() {
        binding = ActivitySingleBinding.inflate(layoutInflater)
    }
}