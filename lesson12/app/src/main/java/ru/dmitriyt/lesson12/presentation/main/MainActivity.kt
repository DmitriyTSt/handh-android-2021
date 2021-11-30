package ru.dmitriyt.lesson12.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.android.support.DaggerAppCompatActivity
import ru.dmitriyt.lesson12.R
import ru.dmitriyt.lesson12.databinding.ActivityMainBinding
import ru.dmitriyt.lesson12.presentation.base.BaseActivity
import ru.dmitriyt.lesson12.presentation.list.BridgeListFragment
import ru.dmitriyt.lesson12.presentation.list.BridgeListViewModel

class MainActivity : BaseActivity() {
    private val binding by viewBinding(ActivityMainBinding::bind)
//    // Пример
//    private val viewModel: MainActivityViewModel by lazy {
//        viewModelFactory.create(MainActivityViewModel::class.java)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view_tag, BridgeListFragment())
            .commit()
    }
}