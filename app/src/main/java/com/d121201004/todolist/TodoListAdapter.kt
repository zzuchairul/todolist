package com.d121201004.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

interface OnDeleteListener {
    fun onDeleteListener(todo: Todo)
    fun onFinishListener(todo: Todo)
}

class TodoListAdapter(onDelete: OnDeleteListener) : ListAdapter<Todo, TodoListAdapter.TodoViewHolder>(TodosComparator()) {
    var onItemClick: OnDeleteListener = onDelete

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, onItemClick)
    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val todoItemTitle: TextView = itemView.findViewById(R.id.todo_title)
        private val todoItemPriority: TextView = itemView.findViewById(R.id.todo_priority)
        private val finishButton: Button = itemView.findViewById(R.id.todo_finish)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.todo_delete)

        fun bind(todo: Todo?, onItemClick: OnDeleteListener) {
            if (todo != null) {
                todoItemTitle.text = todo.todo
                todoItemPriority.text = todo.priority
                deleteButton.setOnClickListener {
                    onItemClick.onDeleteListener(todo)
                }
                finishButton.setOnClickListener {
                    onItemClick.onFinishListener(todo)
                }
            }
        }

        companion object {
            fun create(parent: ViewGroup): TodoViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.todo_rv_item, parent, false)
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