package com.example.tp4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.Todo

class TodoAdapter(
        private val elementAAfficher: List<Todo>,
        private val listener: AdapterTodoListener
) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    interface AdapterTodoListener{fun onItemClick(clickedView: View)}

    class ViewHolder(val elementDeLaListe: View) : RecyclerView.ViewHolder(elementDeLaListe) {
        val todo_tv1: TextView? = elementDeLaListe.findViewById(R.id.todo_tv1)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        view.setOnClickListener{
            listener.onItemClick(view)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentTodo= elementAAfficher[position]
        holder.todo_tv1?.text = currentTodo.titre + ": " + currentTodo.description

    }

    override fun getItemCount(): Int = elementAAfficher.size
}