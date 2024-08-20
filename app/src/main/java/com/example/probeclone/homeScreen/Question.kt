package com.example.probeclone.homeScreen

import androidx.annotation.DrawableRes

data class Question(
    val question:String,
    val tag:String,
    val tip:String,
    @DrawableRes val image:Int,
    val totalTraders:Int,
    val yesAmount:Double,
    val noAmount:Double
)