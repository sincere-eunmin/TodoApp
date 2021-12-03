package dev.sincere.todoapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.sincere.todoapp.core.entity.Todo
import dev.sincere.todoapp.databinding.ItemTodoBinding
import dev.sincere.todoapp.ui.main.MainFragment

class TodoItemAdapter: RecyclerView.Adapter<TodoItemAdapter.TodoItemViewHolder>() {
    var todos: List<Todo> = listOf()
        set(value) {
            val diffCallback = EmployeeDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        val todo = todos[position]
        holder.bind(todo)
    }

    override fun getItemCount(): Int = todos.size

    class TodoItemViewHolder(private val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            itemView.findFragment<MainFragment>().onDetailBtnClicked(todo)
        }
    }

    class EmployeeDiffCallback(private val oldEmployeeList: List<Todo>, private val newEmployeeList: List<Todo>) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldEmployeeList.size
        }

        override fun getNewListSize(): Int {
            return newEmployeeList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldEmployeeList[oldItemPosition] == newEmployeeList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldEmployeeList[oldItemPosition].id == newEmployeeList[newItemPosition].id
        }

    }
}