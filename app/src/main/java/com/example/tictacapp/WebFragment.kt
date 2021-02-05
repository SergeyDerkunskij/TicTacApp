package com.example.tictacapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.fragment_web.*


class WebFragment : Fragment(R.layout.fragment_web) {

    private lateinit var webView: WebView
    private val PREFERENCES = "PREFERENCES"
    private val WEB = "WEB"
    private var url = Network.getCustomURL()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferences: SharedPreferences =
            requireContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        url = preferences.getString(WEB,url)!!

        webView = web_View
        webView.settings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let { view?.loadUrl(it) }
                return true
            }
        }
        webView.loadUrl(url)
        CookieManager.getInstance().setAcceptCookie(true)
    }

    override fun onStop() {
        super.onStop()
        onSaveData()
    }



    private fun onSaveData() {
        val preferences: SharedPreferences =
            requireContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(WEB, webView.url.toString())
        editor.apply()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(webView.canGoBack()){
                    webView.goBack()
                }else{
                    ActivityCompat.finishAffinity(requireActivity())
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

    }
}