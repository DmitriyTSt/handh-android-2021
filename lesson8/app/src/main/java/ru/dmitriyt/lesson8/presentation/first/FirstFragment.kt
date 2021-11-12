package ru.dmitriyt.lesson8.presentation.first

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.dmitriyt.lesson8.R
import ru.dmitriyt.lesson8.databinding.FragmentFirstBinding

class FirstFragment : Fragment(R.layout.fragment_first) {
    companion object {
        fun newInstance(): FirstFragment {
            return FirstFragment()
        }
    }

    private val binding by viewBinding(FragmentFirstBinding::bind)

    private val viewModel: FirstViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.subscribeToBridges(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bridgesLiveData.observe(viewLifecycleOwner) { bridges ->
            binding.textViewBridges.text = buildString {
                bridges.forEach { bridge ->
                    append(bridge.toString())
                    append("\n")
                }
            }
        }

        binding.buttonAddBridge.setOnClickListener { viewModel.createBridge(it.context) }
    }
}