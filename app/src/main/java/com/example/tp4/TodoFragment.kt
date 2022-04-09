package com.example.tp4

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.TodoRepository

class TodoFragment(private val context: MainActivity) : Fragment(), TodoAdapter.AdapterTodoListener {

   // companion object {
    //    fun newInstance() = TodoFragment()
   // }

    private lateinit var viewModel: TodoViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.todo_fragment, container, false)


        val button:Button? = view.findViewById(R.id.bouton)
        val mon_recycler: RecyclerView? = view.findViewById(R.id.mon_recycler)

        val repo = TodoRepository()
        repo.updateData{
            mon_recycler?.layoutManager = LinearLayoutManager(context)
            mon_recycler?.adapter = TodoAdapter(TodoRepository.Singleton.todoList, this)
        }

        button?.setOnClickListener() {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container, AddTodoFragment(context))
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)



    }

    override fun onItemClick(clickedView: View) {
        val layoutMan = LinearLayoutManager(context)
        val position = layoutMan.getPosition(clickedView)
    }


}