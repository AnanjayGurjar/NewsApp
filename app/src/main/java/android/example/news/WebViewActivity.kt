package android.example.news

import android.example.news.adapter.URL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
//    lateinit var adRequest: AdRequest
//    private var mInterstitialAd: InterstitialAd? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        setSupportActionBar(wb_toolBar)

//        MobileAds.initialize(this)
//        adRequest = AdRequest.Builder().build()
        //showAds()


        var url = intent.getStringExtra(URL)
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url.toString())



    }



//    private fun showAds(){
//        InterstitialAd.load(this,"ca-app-pub-9352737427677137/2923987795", adRequest, object : InterstitialAdLoadCallback() {
//            override fun onAdFailedToLoad(adError: LoadAdError) {
//                Log.d("TAG", adError?.message)
//                mInterstitialAd = null
//            }
//
//            override fun onAdLoaded(interstitialAd: InterstitialAd) {
//                Log.d("TAG", "Ad was loaded")
//                mInterstitialAd = interstitialAd
//            }
//        })
//        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
//            override fun onAdDismissedFullScreenContent() {
//                Log.d("TAG", "Ad was dismissed.")
//            }
//
//            override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
//                Log.d("TAG", "Ad failed to show.")
//            }
//
//            override fun onAdShowedFullScreenContent() {
//                Log.d("TAG", "Ad showed fullscreen content.")
//                mInterstitialAd = null
//            }
//        }
//        if (mInterstitialAd != null) {
//            mInterstitialAd?.show(this)
//        } else {
//            Log.d("TAG", "The interstitial ad wasn't ready yet.")
//        }
//    }
}