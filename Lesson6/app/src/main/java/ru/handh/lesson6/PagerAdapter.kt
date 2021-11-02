package ru.handh.lesson6

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    companion object {
        val TITLES = listOf("Таб 1", "Таб 2")
    }

    override fun getItemCount() = TITLES.size

    override fun createFragment(position: Int): Fragment {
        return FirstFragment.newInstance(TITLES[position])
    }
}