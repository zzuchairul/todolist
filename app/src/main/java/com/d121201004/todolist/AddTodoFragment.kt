package com.d121201004.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.activityViewModels

class AddTodoFragment : Fragment() {

    private lateinit var editTodoTitle: EditText
    private lateinit var editTodoPriority: RadioGroup
    private lateinit var priority: String

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
        val view = inflater.inflate(R.layout.fragment_add_todo, container, false)
        editTodoTitle = view.findViewById<EditText>(R.id.editTextTitle)

        editTodoPriority = view.findViewById(R.id.todo_priority_radio)

        val addTodoBtn = view.findViewById<Button>(R.id.addTodo)
        addTodoBtn.setOnClickListener {
            val replyIntent = Intent()
            if(TextUtils.isEmpty(editTodoTitle.text)){
                requireActivity().setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                when(editTodoPriority.checkedRadioButtonId){
                    R.id.pm ->
                        priority = "Penting Mendesak"
                    R.id.ptm ->
                        priority = "Penting Tidak Mendesak"
                    R.id.tpm ->
                        priority = "Tidak Penting Mendesak"
                    R.id.tptm ->
                        priority = "Tidak Penting Tidak Mendesak"
                }

                val word = editTodoTitle.text.toString()
                val todo = Todo(0, word, false, priority)
                todoViewModel.insert(todo)
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout, TodoListFragment()).commit()
            }
        }

        return view
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.todolistsql.REPLY"
    }
}