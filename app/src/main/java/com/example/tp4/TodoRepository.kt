package com.example.tp2

import com.example.tp2.TodoRepository.Singleton.database
import com.example.tp2.TodoRepository.Singleton.todoList
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TodoRepository {

    object Singleton {
        val database = FirebaseDatabase.getInstance().getReference("todos")
        val todoList = arrayListOf<Todo>()
    }

    fun updateData(callback: ()-> Unit){

        val df = database.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                todoList.clear()
                for (ds in snapshot.children){
                    val todo = ds.getValue(Todo::class.java)
                    if (todo != null){
                        todoList.add(todo)
                    }
                }
                callback()
            }

            override fun onCancelled(error : DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    fun insertTodo (todo: Todo) = database.child(todo.id!!).setValue(todo)





}