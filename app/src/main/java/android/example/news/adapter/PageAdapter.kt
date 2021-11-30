package android.example.news.adapter

import android.example.news.fragments.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PageAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {
    var tabCount : Int = behavior

    override fun getCount() = tabCount

    override fun getItem(position: Int): Fragment {
        when (position){
            0 -> return HomeFragment()
            1 -> return SportsFragment()
            2 -> return HealthFragment()
            3 -> return ScienceFragment()
            4 -> return EntertainmentFragment()
            5 -> return TechFragment()

            else -> return HomeFragment()

        }
    }
}