package ru.handh.lesson6

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class FirstFragment : Fragment(R.layout.fragment_first) {

    companion object {
        const val TAG = "FirstFragmentTag"
        private const val EXTRA_BUTTON_TEXT = "extra_button_text"

        fun newInstance(buttonText: String): FirstFragment {
            return FirstFragment().apply {
                arguments = bundleOf(EXTRA_BUTTON_TEXT to buttonText)
            }
        }
    }

    private val buttonText by lazy { arguments?.getString(EXTRA_BUTTON_TEXT).orEmpty() }

    private var firstFragmentListener: FirstFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FirstFragmentListener) {
            firstFragmentListener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editText = view.findViewById<EditText>(R.id.editText)

        view.findViewById<Button>(R.id.button).apply {
            setOnClickListener {
                firstFragmentListener?.onButtonClick(editText.text.toString())
            }
            text = buttonText
        }
    }

    override fun onDetach() {
        firstFragmentListener = null
        super.onDetach()
    }
}