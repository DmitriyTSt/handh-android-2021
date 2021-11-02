package ru.handh.lesson6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity(), FirstFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = FirstFragment.newInstance("")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, FirstFragment.newInstance("Я кнопка"))
            .commit()
    }

    override fun onButtonClick(buttonText: String) {
        val fragment = if (buttonText == "111") {
            SecondFragment.newInstance()
        } else {
            FirstFragment.newInstance(buttonText)
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .addToBackStack(null)
            .commit()
    }
}