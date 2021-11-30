package android.example.news.remote

import android.example.dailynews.remote.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var BASE_URL = "https://newsapi.org/v2/"
class Utilities {

    companion object{
        private var retrofit: Retrofit? = null
        fun getApiInterface(): ApiInterface? {
            if(retrofit == null){
                retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit?.create(ApiInterface::class.java)
        }
    }



}