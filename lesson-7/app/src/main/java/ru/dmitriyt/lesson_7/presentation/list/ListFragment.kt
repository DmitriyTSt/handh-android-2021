package ru.dmitriyt.lesson_7.presentation.list

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import ru.dmitriyt.lesson_7.R
import ru.dmitriyt.lesson_7.data.model.Bridge
import ru.dmitriyt.lesson_7.data.remote.BridgesApi

class ListFragment : Fragment(R.layout.fragment_list) {

    private val progressBar: ProgressBar
        get() = view?.findViewById(R.id.progressBar)!!

    private val textView: TextView
        get() = view?.findViewById(R.id.textView)!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadBridges()
        println("ON_CREATE")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("ON_VIEW_CREATED")
    }

    private fun loadBridges() {
        lifecycleScope.launchWhenStarted {
            println("START_LOAD")
            try {
                setStateLoading()
                delay(2000)
                val bridges = BridgesApi.apiService.getBridges()
                if (bridges.isEmpty()) {
                    setStateEmpty()
                } else {
                    setStateData(bridges)
                }
            } catch (e: Exception) {
                setStateError(e)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        println("ON_START")
    }

    override fun onStop() {
        super.onStop()
        println("ON_STOP")
    }

    private fun setStateLoading() {
        progressBar.isVisible = true
        textView.isVisible = false
    }

    private fun setStateData(bridges: List<Bridge>) {
        progressBar.isVisible = false
        textView.isVisible = true
        textView.text = buildString {
            bridges.forEach { bridge ->
                append("${bridge.id} ${bridge.name}\n")
            }
        }
    }

    private fun setStateEmpty() {
        progressBar.isVisible = false
        textView.isVisible = true
        textView.text = "Пусто"
    }

    private fun setStateError(e: Exception) {
        progressBar.isVisible = false
        textView.isVisible = true
        textView.text = e.message
    }
}