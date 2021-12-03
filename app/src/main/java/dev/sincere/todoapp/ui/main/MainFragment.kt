package dev.sincere.todoapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dev.sincere.todoapp.base.UIState
import dev.sincere.todoapp.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.collect
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.sincere.todoapp.core.entity.Todo
import dev.sincere.todoapp.ui.main.adapter.TodoItemAdapter

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bind(inflater, container)
        observe()
        return binding.root
    }

    override fun onDestroyView() {
        unbind()
        super.onDestroyView()
    }

    private fun bind(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentMainBinding.inflate(inflater, container, false).apply {
            swipeRefresh.setOnRefreshListener {
                viewModel.getTodos()
            }
            todoList.layoutManager = LinearLayoutManager(requireContext())
            addBtn.setOnClickListener {
                viewModel.onAddBtnClicked()
            }
        }
    }

    private fun unbind() {
        _binding = null
    }

    private fun observe() {
        lifecycleScope.launchWhenStarted {
            viewModel.todos.collect { state ->
                when (state) {
                    is UIState.Success -> {
                        val adapter = binding.todoList.adapter as? TodoItemAdapter?: TodoItemAdapter().apply {
                            binding.todoList.adapter = this
                        }
                        adapter.todos = state.data
                        binding.swipeRefresh.isRefreshing = false
                    }
                    is UIState.Error -> {
                        showDialog()
                    }
                    else -> {

                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.event.collect { event ->
                when (event) {
                    is MainViewModel.Event.AddBtnClicked -> {
                        val action = MainFragmentDirections.actionMainFragmentToTodoUpdateFragment()
                        findNavController().navigate(action)
                    }
                    is MainViewModel.Event.DetailBtnClicked -> {
                        val action = MainFragmentDirections.actionMainFragmentToTodoDetailFragment()
                        findNavController().navigate(action)
                    }
                }
            }
        }
    }

    private fun showDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("실패")
        builder.setMessage("데이터를 가져오지 못했습니다.")
        builder.setPositiveButton("확인") { _, _ ->
            requireActivity().finish()
        }
        builder.show()
    }

    fun onDetailBtnClicked(todo: Todo) {
        viewModel.onDetailBtnClicked(todo)
    }

}