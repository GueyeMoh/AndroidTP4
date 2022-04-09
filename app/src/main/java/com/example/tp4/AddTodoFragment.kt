package com.example.tp4

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import com.example.tp2.Todo
import com.example.tp2.TodoRepository
import java.util.*

class AddTodoFragment(private val context: MainActivity): Fragment(), View.OnClickListener {
    var titre: AppCompatEditText? = null
    var description: AppCompatEditText? = null
    //val mainActivity = (activity as? MainActivity)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.add_todo, container, false)
        val ajouter: Button? = view.findViewById(R.id.ajouter)
        val retour: ImageButton? = view.findViewById(R.id.arrow_back)
        titre = view.findViewById(R.id.titre)
        description = view.findViewById(R.id.description)
        ajouter?.setOnClickListener(this)
        retour?.setOnClickListener(this)
        return view
    }


    private fun sendForm(view: View) {
        val repo = TodoRepository()

        val todo = Todo(UUID.randomUUID().toString(), titre?.text.toString(), description?.text.toString())
        if (!todo.titre.isNullOrEmpty() && !todo.description.isNullOrEmpty()) {
            repo.insertTodo(todo)
            Toast.makeText(context, "Todo is added", Toast.LENGTH_LONG).show()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container, TodoFragment(context))
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.arrow_back -> {
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.fragment_container, TodoFragment(context))
                transaction?.addToBackStack(null)
                transaction?.commit()
            }
            R.id.ajouter -> {
                sendForm(v)
            }
        }
    }

}