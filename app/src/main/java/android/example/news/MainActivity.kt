package android.example.news

import android.example.news.adapter.PageAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

//7229c42789a84c96bf766d55da90932a
const val api_key = "4ae45032e9d54e75a5837180419998b5"
const val country = "in"
class MainActivity : AppCompatActivity() {

    lateinit var pageAdapter : PageAdapter
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolBar)
        viewPager = findViewById(R.id.pager)
        pageAdapter = PageAdapter(supportFragmentManager, 6)
        viewPager.adapter = pageAdapter



        tabLayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.setCurrentItem(tab.position)
                if(tab.position == 0 || tab.position == 1 || tab.position == 2 || tab.position == 4 || tab.position == 5){
                    pageAdapter.notifyDataSetChanged()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

    }
}