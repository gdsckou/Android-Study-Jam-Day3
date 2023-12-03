package com.example.myapplication5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication5.databinding.ActivityDiceRollerBinding
import com.example.myapplication5.databinding.ActivityMainBinding

class DiceRollerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiceRollerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiceRollerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val diceClass = DiceClass()

        binding.buttonZar.setOnClickListener {
            val result = (1..6).random()
            diceClass.diceWithButtonAndImage(result)
            binding.diceImage.setImageResource(diceClass.getImageResource())
        }


        }
}
