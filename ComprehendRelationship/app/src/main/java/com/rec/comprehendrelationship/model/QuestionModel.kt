package com.rec.comprehendrelationship.model

data class QuestionModel(
    val question:String,
    val option1:String,
    val option2:String,
    val option3:String,
    val option4:String,
    //val image:Int,
    val answer:String
)
