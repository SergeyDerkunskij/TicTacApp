package com.example.tictacapp

import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_play_field.*
import java.util.*
import kotlin.collections.ArrayList

class SoloPlayerFragment : Fragment(R.layout.fragment_play_field) {

    private var board = arrayListOf<String>("", "", "", "", "", "", "", "", "")

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

    private fun makeMove(buttonPosition: Int, button:Button) {
        if (board[buttonPosition] == "") {
            button.text = "X"
            board[buttonPosition] = "X"
            if (!isBoardFull(board) && !result(board, "X")) {
                val position = getComputerMove(board)
                board[position] = "O"
                displayComputerButton(position)
            }
        }
        resultOut(board)
    }

    private fun getComputerMove(board: ArrayList<String>): Int {

        //check if computer can win in this move
        for (i in 0..board.count()-1){
            val copy: ArrayList<String> = getBoardCopy(board)
            if(copy[i] == "") copy[i] = "O"
            if(result(copy, "O")) return i
        }

        //check if player could win in the next move
        for (i in 0..board.count()-1){
            val copy2 = getBoardCopy(board)
            if(copy2[i] == "") copy2[i] = "X"
            if(result(copy2, "X")) return i
        }

        //try to take corners if its free
        val move = choseRandomMove(board, arrayListOf<Int>(0, 2, 6, 8))
        if(move != -1)
            return move

        //try to take center if its free
        if(board[4] == "") return 4

        //move on one of the sides
        return choseRandomMove(board, arrayListOf<Int>(1, 3, 5, 7))
    }


    private fun choseRandomMove(board: ArrayList<String>, list: ArrayList<Int>): Int {
        val possibleMoves = arrayListOf<Int>()
        for (i in list){
            if(board[i] == "") possibleMoves.add(i)
        }
        return if(possibleMoves.isEmpty()) -1
        else {
            val index = Random().nextInt(possibleMoves.count())
            possibleMoves[index]
        }
    }

    private fun getBoardCopy(board: ArrayList<String>): ArrayList<String> {
        val bd = arrayListOf<String>("", "", "", "", "", "", "", "", "")
        for (i in 0 until board.count()) {
            bd[i] = board[i]
        }
        return bd
    }

    private fun isBoardFull(board: ArrayList<String>): Boolean {
        for (i in board)
            if(i != "X" && i != "O") return false
        return true
    }


    private fun resultOut(board: ArrayList<String>){
        if(result(board, "X")){
            val action = SoloPlayerFragmentDirections
                .actionSoloPlayerFragmentToResultFragment("YOU")
            findNavController().navigate(action)
        }else if(result(board, "O")){
            val action = SoloPlayerFragmentDirections
                .actionSoloPlayerFragmentToResultFragment("COMPUTER")
            findNavController().navigate(action)
        }
        if(isBoardFull(board)){
            val action = SoloPlayerFragmentDirections
                .actionSoloPlayerFragmentToResultFragment("DRAW")
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


    private fun displayComputerButton(position: Int){
        when (position) {
            0 -> button0.text = "O"
            1 -> button1.text = "O"
            2 -> button2.text = "O"
            3 -> button3.text = "O"
            4 -> button4.text = "O"
            5 -> button5.text = "O"
            6 -> button6.text = "O"
            7 -> button7.text = "O"
            8 -> button8.text = "O"
        }
    }



}