package com.example.tictactoelm

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val playerOne = "X"
    private val playerTwo = "O"
    private var turn = 1
    private lateinit var turnStatus: String
    private lateinit var turnText: TextView

    private val checkWin = Array<String>(9){it.toString()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        turnStatus = "$playerOne's turn"

        turnText = findViewById(R.id.turnText)
        turnText.text = turnStatus
    }

    private var isplayerOneTurn = true

    fun btnClick(v: View?) {
        val btn = v as Button
        val isWinner = playGame(btn)
        if(isWinner) {
            Toast.makeText(applicationContext, "${if(isplayerOneTurn) "X" else "O"} Won", Toast.LENGTH_SHORT).show()
            finish()
            startActivity(intent)
        }
        else if(!areTurnsLeft()) {
            Toast.makeText(applicationContext, "Nobody Won", Toast.LENGTH_SHORT).show()
            finish()
            startActivity(intent)
        }
        isplayerOneTurn = !isplayerOneTurn
        turn += 1
    }

    private fun playGame(btn: Button) : Boolean {
        val tag = btn.tag.toString()
        if (isplayerOneTurn) {
            btn.text = "X"
            turnStatus = "$playerTwo's turn"
            turnText.text = turnStatus
            checkWin[tag.toInt()] = playerOne
        } else {
            btn.text = "O"
            turnStatus = "$playerOne's turn"
            turnText.text = turnStatus
            checkWin[tag.toInt()] = playerTwo
        }
        btn.isEnabled = false
        return checkWin(checkWin)
    }

    private fun checkWin(data: Array<String>) : Boolean {
        val cond1 = data[0] == data[1] && data[0] == data[2]
        val cond2 = data[3] == data[4] && data[3] == data[5]
        val cond3 = data[6] == data[7] && data[6] == data[8]

        val cond4 = data[0] == data[3] && data[0] == data[6]
        val cond5 = data[1] == data[4] && data[1] == data[7]
        val cond6 = data[2] == data[5] && data[2] == data[8]

        val cond7 = data[0] == data[4] && data[0] == data[8]
        val cond8 = data[2] == data[4] && data[2] == data[6]

        val condList = arrayOf(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8)

        return condList.any {it}
    }

    private fun areTurnsLeft() : Boolean{
        return turn < 9
    }

}