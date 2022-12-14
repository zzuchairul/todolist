package com.d121201004.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.d121201061.priotodoapp.databinding.ActivityMainBinding

class TodoHistoryFragment : Fragment() {

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
        var view = inflater.inflate(R.layout.fragment_todo_history, container, false)

        val todoRv = view.findViewById<RecyclerView>(R.id.todoHistoryRv)
        val adapter = TodoHistoryAdapter()
        todoRv.adapter = adapter
        todoRv.layoutManager = LinearLayoutManager(view.context)

        todoViewModel.allHistory.observe(viewLifecycleOwner) { todos ->
            todos.let { adapter.submitList(it) }
        }

        return view
    }
}