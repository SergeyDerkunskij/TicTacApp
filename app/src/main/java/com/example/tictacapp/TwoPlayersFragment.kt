package com.example.tictacapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_play_field.*
import java.util.*
import kotlin.collections.ArrayList


class TwoPlayersFragment : Fragment(R.layout.fragment_play_field) {


    private var board = arrayListOf<String>("", "", "", "", "", "", "", "", "")
    private var chance = "X"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        board = if (savedInstanceState == null) board
        else savedInstanceState.getStringArrayList("Key")?.toList() as ArrayList<String>

        fillTheField()

        button0.setOnClickListener { makeMove(0, it as Button) }

        button1.setOnClickListener { makeMove(1, it as Button) }

        button2.setOnClickListener { makeMove(2, it as Button) }

        button3.setOnClickListener { makeMove(3, it as Button) }

        button4.setOnClickListener { makeMove(4, it as Button) }

        button5.setOnClickListener { makeMove(5, it as Button) }

        button6.setOnClickListener { makeMove(6, it as Button) }

        button7.setOnClickListener { makeMove(7, it as Button) }

        button8.setOnClickListener { makeMove(8, it as Button) }

        buttonReset.setOnClickListener{
            board = arrayListOf<String>("", "", "", "", "", "", "", "", "")
            fillTheField()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("Key", board)
    }

    private fun fillTheField(){
        button0.text = board[0]
        button1.text = board[1]
        button2.text = board[2]
        button3.text = board[3]
        button4.text = board[4]
        button5.text = board[5]
        button6.text = board[6]
        button7.text = board[7]
        button8.text = board[8]
    }

    private fun makeMove(buttonPosition: Int, button: Button) {

        if (board[buttonPosition] != "")
        else if (chance == "X") {
            button.text = "X"
            board[buttonPosition] = "X"
            chance = "O"
        } else {
            button.text = "O"
            board[buttonPosition] = "O"
            chance = "X"
        }
        resultOut(board)
    }


    private fun isBoardFull(board: ArrayList<String>): Boolean {
        for (i in board)
            if(i != "X" && i != "O") return false
        return true
    }


    private fun resultOut(board: ArrayList<String>){
        if(result(board, "X")){
            val action = TwoPlayersFragmentDirections
                .actionTwoPlayersFragmentToResultFragment("X")
            findNavController().navigate(action)
        }else if(result(board, "O")){
            val action = TwoPlayersFragmentDirections
                .actionTwoPlayersFragmentToResultFragment("O")
            findNavController().navigate(action)
        }
        if(isBoardFull(board)){
            val action = TwoPlayersFragmentDirections
                .actionTwoPlayersFragmentToResultFragment("DRAW")
            findNavController().navigate(action)
        }
    }




    private fun result(bd: ArrayList<String>, s: String): Boolean =
        if(bd[0] == s && bd[1] == s && bd[2] == s) true
        else if(bd[3] == s && bd[4] == s && bd[5] == s) true
        else if(bd[6] == s && bd[7] == s && bd[8] == s) true
        else if(bd[0] == s && bd[3] == s && bd[6] == s) true
        else if(bd[1] == s && bd[4] == s && bd[7] == s) true
        else if(bd[2] == s && bd[5] == s && bd[8] == s) true
        else if(bd[0] == s && bd[4] == s && bd[8] == s) true
        else bd[2] == s && bd[4] == s && bd[6] == s

}