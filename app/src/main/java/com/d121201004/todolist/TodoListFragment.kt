package com.d121201004.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.d121201061.priotodoapp.databinding.ActivityMainBinding
import com.d121201061.priotodoapp.databinding.FragmentTodoListBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoListFragment : Fragment() {

    private val newTodoActivityRequestCode = 1
    private val todoViewModel: TodoViewModel by activityViewModels {
        TodoViewModelFactory((requireContext().applicationContext as TodoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_todo_list, container, false)

        val todoRv = view.findViewById<RecyclerView>(R.id.todoListRv)
        val adapter = TodoListAdapter(object : OnDeleteListener {
            override fun onDeleteListener(todo: Todo) {
                todoViewModel.delete(todo)
            }

            override fun onFinishListener(todo: Todo) {
                todoViewModel.finish(todo)
            }
        })
        todoRv.adapter = adapter
        todoRv.layoutManager = LinearLayoutManager(view.context)

        todoViewModel.allTodos.observe(viewLifecycleOwner) { todos ->
            todos.let { adapter.submitList(it) }
        }

        var fabButton = view.findViewById<FloatingActionButton>(R.id.addTodoFab)
        fabButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout, AddTodoFragment()).commit()
        }
        return view
    }

}