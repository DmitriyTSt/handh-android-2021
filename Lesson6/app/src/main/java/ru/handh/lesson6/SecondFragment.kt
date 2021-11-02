package ru.handh.lesson6

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SecondFragment : Fragment(R.layout.fragment_second) {

    companion object {
        fun newInstance(): SecondFragment {
            return SecondFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)

        viewPager.adapter = PagerAdapter(this)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewPager.isUserInputEnabled = position == 0
            }
        })
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = PagerAdapter.TITLES[position]
        }.attach()
    }
}