package com.example.tictacapp

import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

object Network {

    private val client = OkHttpClient()

    fun getCustomSiteCall(): Call {
        val url = HttpUrl.Builder()
            .scheme("https")
            .host("scnddmn.com")
            .addPathSegment("7vZTBtvQ")
            .addQueryParameter("sub1", "google")
            .build()
        val request = Request.Builder().get().url(url).build()

        return client.newCall(request)
    }

    fun getDeepLinkSiteCall(nameDeepLink : String,deepLink : String): Call {
        val url = HttpUrl.Builder()
            .scheme("https")
            .host("scnddmn.com")
            .addPathSegment("7vZTBtvQ")
            .addQueryParameter(nameDeepLink, deepLink)
            .build()
        val request = Request.Builder().get().url(url).build()

        return client.newCall(request)
    }

    fun getCustomURL():String{
         val url = HttpUrl.Builder()
            .scheme("https")
            .host("scnddmn.com")
            .addPathSegment("7vZTBtvQ")
            .addQueryParameter("sub1", "google")
            .build().toString()
        return url
    }

}