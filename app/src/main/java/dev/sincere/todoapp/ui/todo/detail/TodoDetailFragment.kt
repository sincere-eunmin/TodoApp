package dev.sincere.todoapp.ui.todo.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.sincere.todoapp.R

class TodoDetailFragment : Fragment() {

    companion object {
        fun newInstance() = TodoDetailFragment()
    }

    private lateinit var viewModel: TodoDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todo_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TodoDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}