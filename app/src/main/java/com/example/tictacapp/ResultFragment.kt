package com.example.tictacapp

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_result.*


class ResultFragment : Fragment(R.layout.fragment_result) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val args : ResultFragmentArgs by navArgs()

        val player = args.gameResult
        if(player == "DRAW") textViewWon.text = "DRAW"
        else textViewWon.text = "$player WON"

        Handler().postDelayed({
            findNavController().navigate(R.id.action_resultFragment_to_startFragment)
        }, 2000)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

    }


}