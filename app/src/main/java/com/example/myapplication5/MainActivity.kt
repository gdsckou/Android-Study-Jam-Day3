package com.example.myapplication5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication5.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var array : Array<String>
    var playerResult = 0
    var opponentResult = 0
    lateinit var playerImage : ImageView
    lateinit var opponentImage : ImageView
    var playerChoice = ""

    lateinit var txtPlayer : TextView
    lateinit var txtOpponent : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playerImage = binding.imageViewPlayer
        opponentImage = binding.imageViewOpponent

        txtPlayer = binding.txtPlayerCount
        txtOpponent = binding.txtOpponentCount

        array = resources.getStringArray(R.array.game1)

        binding.btnPlay.setOnClickListener {
            if (playerChoice == "")
            {
                Toast.makeText(this,"Önce taş,kağıt,makas seçin",Toast.LENGTH_SHORT).show()
            }
           playRockPaperScissors(playerChoice)
        }

        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,array)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerPlayer.adapter = adapter

        binding.spinnerPlayer.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                playerChoice = array[position]
                setDrawableForPlayer(playerChoice)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.btnNextGame.setOnClickListener {
            val intent = Intent(this@MainActivity,DiceRollerActivity::class.java)
            startActivity(intent)
        }

    }

    fun playRockPaperScissors(choice : String)
    {
        val opponentChoice = array.random()
        setDrawableForOpponent(opponentChoice)

        if (choice == opponentChoice)
        {
            Toast.makeText(this,"Berabere kaldınız",Toast.LENGTH_SHORT).show()
        }

        if( choice == "Taş")
        {
            if (opponentChoice == "Kağıt")
            {
                opponentResult++
                Toast.makeText(this,"Kaybettin",Toast.LENGTH_SHORT).show()
            }
            else if(opponentChoice == "Makas")
            {
                playerResult++
                Toast.makeText(this,"Kazandın tebrikler",Toast.LENGTH_SHORT).show()

            }
        }

        if( choice == "Makas")
        {
            if (opponentChoice == "Taş")
            {
                opponentResult++
                Toast.makeText(this,"Kaybettin",Toast.LENGTH_SHORT).show()
            }
            else if(opponentChoice == "Kağıt")
            {
                playerResult++
                Toast.makeText(this,"Kazandın tebrikler",Toast.LENGTH_SHORT).show()

            }
        }

        if( choice == "Kağıt")
        {
            if (opponentChoice == "Makas")
            {
                opponentResult++
                Toast.makeText(this,"Kaybettin",Toast.LENGTH_SHORT).show()
            }
            else if(opponentChoice == "Taş")
            {
                playerResult++
                Toast.makeText(this,"Kazandın tebrikler",Toast.LENGTH_SHORT).show()

            }
        }

        txtPlayer.text = playerResult.toString()
        txtOpponent.text = opponentResult.toString()
        checkResult()
    }

    fun setDrawableForOpponent(opponentResult : String)
    {
        when(opponentResult){
            "Taş" -> opponentImage.setImageResource(R.drawable.rock)
            "Kağıt" -> opponentImage.setImageResource(R.drawable.paper)
            "Makas" -> opponentImage.setImageResource(R.drawable.scissors)
            else -> opponentImage.setImageResource(R.drawable.rock)
        }
    }

    fun setDrawableForPlayer(playerChoice : String)
    {
        when(playerChoice){
            "Taş" -> playerImage.setImageResource(R.drawable.rock)
            "Kağıt" -> playerImage.setImageResource(R.drawable.paper)
            "Makas" -> playerImage.setImageResource(R.drawable.scissors)
            else -> opponentImage.setImageResource(R.drawable.rock)
        }
    }

    fun checkResult()
    {
        if (playerResult == 3)
        {
            resetGame()
            Snackbar.make(binding.root,"Oyunu sen kazandın tebrikler",Snackbar.LENGTH_LONG).show()
        }
        else if (opponentResult == 3)
        {
            resetGame()
            Snackbar.make(binding.root,"Oyunu sen kaybettin tekrar dene",Snackbar.LENGTH_LONG).show()
        }
    }

    fun resetGame()
    {
        playerResult = 0
        opponentResult = 0
        txtPlayer.text = playerResult.toString()
        txtOpponent.text = opponentResult.toString()

    }
}