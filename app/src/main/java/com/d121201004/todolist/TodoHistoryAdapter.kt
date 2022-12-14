package com.d121201004.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TodoHistoryAdapter() : ListAdapter<Todo, TodoHistoryAdapter.TodoViewHolder>(TodosComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val todoItemView: TextView = itemView.findViewById(R.id.todo_title)
        private val todoItemPriority: TextView = itemView.findViewById(R.id.todo_priority)

        fun bind(todo: Todo?) {
            if (todo != null) {
                todoItemView.text = todo.todo
                todoItemPriority.text = todo.priority
            }
        }

        companion object {
            fun create(parent: ViewGroup): TodoViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.todo_history_rv_item, parent, false)
                return TodoViewHolder(view)
            }
        }
    }

    class TodosComparator : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.todo == newItem.todo
        }
    }

}
