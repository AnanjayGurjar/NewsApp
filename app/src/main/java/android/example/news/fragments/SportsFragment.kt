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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SportsFragment: Fragment() {
    private lateinit var list: ArrayList<Articles>
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var rv_sports: RecyclerView
    private lateinit var shimmer_sports: ShimmerFrameLayout
    private lateinit var admobNativeAdAdapter: AdmobNativeAdAdapter
    private lateinit var swipe: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_sports, container, false)
        shimmer_sports = view.findViewById(R.id.shimmer_sports)
        swipe = view.findViewById(R.id.swipe_sports)
        shimmer_sports.startShimmer()
        list = ArrayList()
        rv_sports  = view.findViewById(R.id.rv_sports)
        rv_sports.layoutManager =  LinearLayoutManager(context)

        newsAdapter = NewsAdapter(requireContext(), list)
        admobNativeAdAdapter = AdmobNativeAdAdapter.Builder.with(
            "ca-app-pub-3940256099942544/2247696110",
            newsAdapter,
            "medium"
        ).adItemInterval(5)
            .build()


        swipe.setOnRefreshListener{
            fetchNews()
        }

        rv_sports.adapter = newsAdapter
        fetchNews()

        return view

    }

    private fun fetchNews() {
        Utilities.getApiInterface()?.getCategoryNews(country,"sports",  100, api_key )?.enqueue(object :
            Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {

                if(response.isSuccessful){
                    swipe.isRefreshing = false
                    shimmer_sports.stopShimmer()
                    shimmer_sports.visibility = View.GONE
                    rv_sports.visibility = View.VISIBLE
                    list.addAll(response.body()?.articles as ArrayList<Articles>)
                    admobNativeAdAdapter.notifyDataSetChanged()
                }

            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                swipe.isRefreshing = false
                shimmer_sports.stopShimmer()
                shimmer_sports.visibility = View.GONE
                Toast.makeText(context, "Error fetching news", Toast.LENGTH_SHORT).show()
            }

        })
    }
}