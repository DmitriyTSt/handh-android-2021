package ru.dmitriyt.lesson12.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.dmitriyt.lesson12.R
import ru.dmitriyt.lesson12.presentation.list.BridgeListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view_tag, BridgeListFragment())
            .commit()
    }


}