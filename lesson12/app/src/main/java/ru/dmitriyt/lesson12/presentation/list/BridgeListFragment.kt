package ru.dmitriyt.lesson12.presentation.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.dmitriyt.lesson12.R
import ru.dmitriyt.lesson12.data.model.Bridge
import ru.dmitriyt.lesson12.data.model.BridgeListState
import ru.dmitriyt.lesson12.databinding.FragmentBridgeListBinding

class BridgeListFragment : Fragment(R.layout.fragment_bridge_list) {

    private val binding by viewBinding(FragmentBridgeListBinding::bind)
    private val viewModel: BridgeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.loadBridges()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bridgesLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                BridgeListState.Loading -> setStateLoading()
                is BridgeListState.Data -> {
                    if (state.bridges.isNotEmpty()) {
                        setStateData(state.bridges)
                    } else {
                        setStateEmpty()
                    }
                }
                is BridgeListState.Error -> setStateError(state.exception)
            }
        }
    }

    private fun setStateLoading() = with(binding) {
        progressBar.isVisible = true
        textView.isVisible = false
    }

    private fun setStateData(bridges: List<Bridge>) = with(binding) {
        progressBar.isVisible = false
        textView.isVisible = true
        textView.text = buildString {
            bridges.forEach { bridge ->
                append("${bridge.id} ${bridge.name}\n")
            }
        }
    }

    private fun setStateEmpty() = with(binding) {
        progressBar.isVisible = false
        textView.isVisible = true
        textView.text = "Пусто"
    }

    private fun setStateError(e: Exception) = with(binding) {
        progressBar.isVisible = false
        textView.isVisible = true
        textView.text = e.message
    }
}