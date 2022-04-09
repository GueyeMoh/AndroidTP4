package com.example.tp4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.tp2.TodoRepository
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(TodoFragment(this))

        //importer le bottom nav view
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation)
        navigationView.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.home_page -> {
                    loadFragment(TodoFragment(this))
                    //return@setOnNavigationItemReselectedListener
                }
                R.id.add_page -> {
                    loadFragment(AddTodoFragment(this))
                   // return@setOnNavigationItemReselectedListener
                }
                else-> false

            }

        }
    }

     fun loadFragment(fragment: Fragment) {
        val repo = TodoRepository()
        repo.updateData {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }


}