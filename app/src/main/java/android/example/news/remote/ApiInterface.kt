package android.example.dailynews.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("top-headlines")
    fun getNews(
        @Query("country") country: String,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ): Call<NewsModel>

    @GET("top-headlines")
    fun getCategoryNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ): Call<NewsModel>

}
