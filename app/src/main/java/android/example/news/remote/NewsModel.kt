package android.example.dailynews.remote

data class NewsModel(
    var status: String,
    var totalResults: Int,
    var articles: ArrayList<Articles>
)
data class Articles(
    var author: String,
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String,
    var content: String
)
