package com.example.tictacapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class LoadingFragment : Fragment(R.layout.fragment_loading) {

    private var responseCode = 1

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (isNetworkAvailable()) {
            Thread {
                val response = Network.getCustomSiteCall().execute()
                responseCode = response.code
                if (responseCode!=404) {
                    openChrome("https://scnddmn.com/7vZTBtvQ?sub1=google")
                }

            }.start()
        }

        val handler = Handler()
        handler.postDelayed({
            if ((responseCode==404)||(!isNetworkAvailable())) {
                findNavController().navigate(R.id.action_loadingFragment_to_startFragment)
            }
        }, 2000)

    }

    private fun openChrome(url:String){
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(resources.getColor(R.color.colorButton, null))
        val intent = builder.build()
        intent.launchUrl(requireContext(), Uri.parse(url))
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo

        if(activeNetwork?.isConnected!=null){
            return activeNetwork.isConnected
        }
        else{
            return false
        }
    }
}