package dev.sincere.todoapp.ui.todo.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.sincere.todoapp.databinding.FragmentTodoUpdateBinding

class TodoUpdateFragment : Fragment() {

    private var _binding: FragmentTodoUpdateBinding? = null
    private val binding: FragmentTodoUpdateBinding get() = _binding!!

    private lateinit var viewModel: TodoUpdateViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bind(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        unbind()
        super.onDestroyView()
    }

    private fun bind(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentTodoUpdateBinding.inflate(inflater, container, false)
    }

    private fun unbind() {
        _binding = null
    }

}