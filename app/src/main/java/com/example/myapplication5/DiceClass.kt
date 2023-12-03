package com.example.myapplication5

class DiceClass {

    private var imageResource = 0

    fun diceWithButtonAndImage(result:Int) {
        imageResource = when (result) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }

    fun getImageResource():Int{
        return imageResource
    }
}