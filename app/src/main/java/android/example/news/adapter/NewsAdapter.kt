package android.example.news.adapter

import android.content.Context
import android.content.Intent
import android.example.dailynews.remote.Articles
import android.example.news.R
import android.example.news.WebViewActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val URL = "url"
class NewsAdapter(context: Context, list: ArrayList<Articles>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var context = context
    var list = list



    inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var news_item = itemView.findViewById<CardView>(R.id.cardView)
        var tv_heading = itemView.findViewById<TextView>(R.id.tv_mainHeading)
        var  tv_content = itemView.findViewById<TextView>(R.id.tv_content)
        var tv_author = itemView.findViewById<TextView>(R.id.tv_author)
        var tv_time = itemView.findViewById<TextView>(R.id.tv_time)
        var iv_news = itemView.findViewById<ImageView>(R.id.iv_news)

    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.layout_item, null/*parent*/, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        holder.news_item.setOnClickListener {
            var intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra(URL, list.get(position).url)
            context.startActivity(intent)
        }
        holder.tv_time.text = "Published at ${list.get(position).publishedAt}"
        holder.tv_heading.text = list[position].title
        holder.tv_content.text = list[position].description
        holder.tv_author.text = list[position].author

        Glide.with(context).load(list[position].urlToImage).into(holder.iv_news)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}