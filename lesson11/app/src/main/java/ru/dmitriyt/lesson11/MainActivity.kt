package ru.dmitriyt.lesson11

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import ru.dmitriyt.lesson11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var changed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() = with(binding) {
        buttonTest.setOnClickListener {
            var enabled = true
            if (enabled) {
                val set = AnimatorInflater.loadAnimator(
                    baseContext,
                    R.animator.property_animator
                ) as AnimatorSet
                set.setTarget(textView)
                set.start()
                return@setOnClickListener
            }

            enabled = false
            if (enabled) {
                val xmlAnimator = AnimatorInflater.loadAnimator(
                    baseContext,
                    R.animator.value_animator
                ) as ValueAnimator
                xmlAnimator.addUpdateListener { updatedAnimation ->
                    val animatedValue = updatedAnimation.animatedValue as Float
                    textView.translationX = animatedValue
                }
                xmlAnimator.start()
            }
        }

        val constraintSet1 = ConstraintSet()
        constraintSet1.clone(constraintLayout)

        val constraintSet2 = ConstraintSet()
        constraintSet2.clone(this@MainActivity, R.layout.alt)

        twoLinesTextView.setOnClickListener {
            TransitionManager.beginDelayedTransition(constraintLayout)
            val constraint = if (changed) constraintSet1 else constraintSet2
            constraint.applyTo(constraintLayout)
            changed = !changed
        }
    }
}