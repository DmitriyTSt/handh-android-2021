package ru.dmitriyt.lesson_7.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.dmitriyt.lesson_7.R
import ru.dmitriyt.lesson_7.presentation.list.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view_tag, ListFragment())
            .commit()
    }


}