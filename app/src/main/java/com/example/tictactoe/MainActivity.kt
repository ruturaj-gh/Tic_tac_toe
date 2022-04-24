package com.example.tictactoe


import androidx.appcompat.app.AppCompatActivity


import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    enum class Turn
    {

        NOUGHT,
        CROSS

    }

    private var firstTurn =Turn.CROSS
    private var currentTurn =Turn.CROSS

    private var crossesScore=0
    private var noughtScore=0






    private lateinit var binding: ActivityMainBinding
    private var boardList= mutableListOf<Button>()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
    }


    private fun initBoard() {


        boardList.add(binding.a1)
        boardList.add(binding.a2)
        boardList.add(binding.a3)
        boardList.add(binding.b1)
        boardList.add(binding.b2)
        boardList.add(binding.b3)
        boardList.add(binding.c1)
        boardList.add(binding.c2)
        boardList.add(binding.c3)
    }
    fun boardTapped(view: View){
        if(view !is Button){
            return}
            addToBoard(view)


            if(checkForVictory(NOUGHT)){
                noughtScore++
                result("NOUGHTS WON")
            }
            else if(checkForVictory(CROSS))
            {
                crossesScore++
                result("CROSSES WON")
            }
            if(fullBoard())
            {
                result("Draw")
            }
            
        }


    private fun checkForVictory(S: String): Boolean {

        //horizontal victory
        if(match(binding.a1,S) && match(binding.a2,S) && match(binding.a3,S) )
            return true

        if(match(binding.b1,S) && match(binding.b2,S) && match(binding.b3,S))

            return true
        if(match(binding.c1,S) && match(binding.c2,S) && match(binding.c3,S))
            return true


        //vertical victory

        if(match(binding.a1,S) && match(binding.b1,S) && match(binding.c1,S))
            return true

        if(match(binding.a2,S) && match(binding.b2,S) && match(binding.c2,S))
            return true

        if(match(binding.a3,S) && match(binding.b3,S) && match(binding.c3,S))
            return true



        //diagonal victory
        if(match(binding.a1,S) && match(binding.b2,S) && match(binding.c3,S))
            return true

        if(match(binding.a3,S) && match(binding.b2,S) && match(binding.c1,S))
            return true



        return false

    }
    private fun match(button: Button,symbol:String):Boolean=button.text==symbol

    private fun result(title: String) {
        var message="\n Noughts $noughtScore \n\n Crosses $crossesScore"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Reset")
            { _,_ ->
                resetBoard()
            }.setCancelable(false)
            .show()



    }

    private fun resetBoard() {
        for(button in boardList){
            button.text=""

        }
        if(firstTurn== Turn.NOUGHT)
            firstTurn= Turn.CROSS
        else if(firstTurn== Turn.NOUGHT)
            firstTurn= Turn.NOUGHT
        currentTurn=firstTurn
        setTurnLabel()
    }

    private fun fullBoard(): Boolean {
        for(button in boardList){
            if(button.text=="")
                return false
        }
        return true

    }

    private fun addToBoard(button: Button) {
        if(button.text != ""){
            return
        }
        if(currentTurn== Turn.NOUGHT)
        {

            button.text= NOUGHT
            currentTurn= Turn.CROSS

        }
        else if(currentTurn== Turn.CROSS){
            button.text= CROSS
            currentTurn= Turn.NOUGHT

        }
        setTurnLabel()


    }

    private fun setTurnLabel() {
        var turnText=""
        if(currentTurn == Turn.CROSS)
            turnText="Turn $CROSS"
        else if(currentTurn== Turn.NOUGHT)
            turnText="Turn $NOUGHT"

        binding.turnTv.text=turnText
    }
    companion object
    {
        const val NOUGHT="O"
        const val CROSS = "X"

    }



}