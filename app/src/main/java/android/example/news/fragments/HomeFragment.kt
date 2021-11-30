package android.example.news.fragments

import android.example.dailynews.remote.Articles
import android.example.dailynews.remote.NewsModel
import android.example.news.R
import android.example.news.adapter.NewsAdapter
import android.example.news.api_key
import android.example.news.country
import android.example.news.remote.Utilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.efaso.admob_advanced_native_recyvlerview.AdmobNativeAdAdapter
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment: Fragment() {
    private lateinit var list: ArrayList<Articles>
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var rv_home: RecyclerView
    private lateinit var shimmer: ShimmerFrameLayout
    private lateinit var admobNativeAdAdapter: AdmobNativeAdAdapter
    private lateinit var swipe: SwipeRefreshLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)

        shimmer = view.findViewById(R.id.shimmer_home)
        swipe = view.findViewById(R.id.swipe_home)
        shimmer.startShimmer()
        list = ArrayList()
        rv_home = view.findViewById(R.id.rv_home)
        rv_home.layoutManager =  LinearLayoutManager(context)
        newsAdapter = NewsAdapter(requireContext(), list)

        swipe.setOnRefreshListener{
            fetchNews()
        }


        admobNativeAdAdapter = AdmobNativeAdAdapter.Builder.with(
            "ca-app-pub-3940256099942544/2247696110",
            newsAdapter,
            "medium"
        ).adItemInterval(5)
            .build()


        rv_home.adapter = admobNativeAdAdapter
        fetchNews()
        return view

    }

    private fun fetchNews() {
        Utilities.getApiInterface()?.getNews(country, 100, api_key)?.enqueue(object : Callback<NewsModel>{
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                if(response.isSuccessful){
                    swipe.isRefreshing = false
                    shimmer_home.stopShimmer()
                    shimmer.visibility = View.GONE
                    rv_home.visibility = View.VISIBLE
                    list.addAll(response.body()?.articles as ArrayList<Articles>)

                    admobNativeAdAdapter.notifyDataSetChanged()


                }

            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                swipe.isRefreshing = false
                shimmer_home.stopShimmer()
                shimmer.visibility = View.GONE
                Toast.makeText(context, "Error fetching news", Toast.LENGTH_SHORT).show()
            }

        })

    }
}