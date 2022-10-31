package com.rec.comprehendrelationship.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rec.comprehendrelationship.R
import com.rec.comprehendrelationship.databinding.ActivityMainBinding
import com.rec.comprehendrelationship.model.QuestionModel
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var index:Int = 0
    lateinit var questionsList:ArrayList<QuestionModel>
    lateinit var questionModel: QuestionModel

    private var correctAnswerCount:Int = 0
    private var wrongAnswerCount:Int = 0

    private var backPressedTime: Long = 0
    private var backToast: Toast? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        questionsList = ArrayList()

        questionsList.add(
            QuestionModel(
            "Как лучше перевести To succeed in?",
            "Преуспевать в чем-то",
            "Для достижения успеха в",
            "добиваться успеха в",
            "Стать успешным",
            "Преуспевать в чем-то"
            )
        )

        questionsList.add(
            QuestionModel(
            "Что означает To constitute a part of ?",
            "Составлять часть",
            "Являться частью",
            "Составляет значительную часть",
            "Будет действительно признана таковой.",
            "Составлять часть"
            )
        )

        questionsList.add(
            QuestionModel(
            "Как лучше перевести To keep down ?",
            "Ограничить",
            "Держать при себе",
            "Вымещать злобу",
            "Сдерживаться",
            "Сдерживаться"
            )
        )

        questionsList.add(
            QuestionModel(
            "Как лучше перевести To get on well ?",
            "Уживаться",
            "Ладить",
            "Неплохо ладить",
            "Успешно жить",
            "Уживаться"
            )
        )
        // 5
        questionsList.add(
            QuestionModel(
            "Что означает To be in good temper?",
            "Быть в хорошем настроении",
            "Зарядиться хорошим настроением.",
            "Хорошем расположении духа",
            "Добрый нрав",
            "Быть в хорошем настроении"
            )
        )

        questionsList.shuffle()
        questionModel= questionsList[index]

        setAllQuestions()
        countdown()
    }


    fun countdown() {
        val duration:Long=TimeUnit.SECONDS.toMillis(15)

        object :CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val sDuration:String= String.format(
                    Locale.ENGLISH, "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))
                )
                binding.countdown.text = sDuration
            }

            override fun onFinish() {
                index++
                if (index<questionsList.size){
                    questionModel=questionsList[index]
                    setAllQuestions()
                    resetBackground()
                    enableButton()
                    countdown()
                } else {
                    gameResult()
                }
            }
        }.start()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun correctAns(option: Button) {
        option.background = getDrawable(R.drawable.bg_validate_right)
        correctAnswerCount++
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun wrongAns(option:Button) {
        option.background = getDrawable(R.drawable.bg_validate_wrong)
        wrongAnswerCount++
    }

    private fun gameResult() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("correct",correctAnswerCount.toString())
        intent.putExtra("total",questionsList.size.toString())
        startActivity(intent)
    }

    private fun setAllQuestions() {
        binding.questions.text = questionModel.question
        binding.option1.text = questionModel.option1
        binding.option2.text = questionModel.option2
        binding.option3.text = questionModel.option3
        binding.option4.text = questionModel.option4
    }

    private fun enableButton() {
        binding.option1.isClickable = true
        binding.option2.isClickable = true
        binding.option3.isClickable = true
        binding.option4.isClickable = true
    }

    private fun disableButton() {
        binding.option1.isClickable = false
        binding.option2.isClickable = false
        binding.option3.isClickable = false
        binding.option4.isClickable = false
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun resetBackground() {
        binding.option1.background =  getDrawable(R.drawable.bg_validate_empty)
        binding.option2.background =  getDrawable(R.drawable.bg_validate_empty)
        binding.option3.background =  getDrawable(R.drawable.bg_validate_empty)
        binding.option4.background = getDrawable(R.drawable.bg_validate_empty)
    }

     @SuppressLint("UseCompatLoadingForDrawables")
     fun option1Clicked(view:View){
        disableButton()
        if(questionModel.option1 == questionModel.answer){
            binding.option1.background = getDrawable(R.drawable.bg_validate_right)
            correctAns( binding.option1)
        } else {
            wrongAns( binding.option1)
        }

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun option2Clicked(view:View){
        disableButton()
        if(questionModel.option2 == questionModel.answer){
            binding.option2.background = getDrawable(R.drawable.bg_validate_right)
            correctAns( binding.option2)
        } else {
            wrongAns( binding.option2)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun option3Clicked(view:View){
        disableButton()
        if(questionModel.option3 == questionModel.answer){
            binding.option3.background= getDrawable(R.drawable.bg_validate_right)
            correctAns( binding.option3)
        } else {
            wrongAns( binding.option3)
        }
    }

    @SuppressLint("SuspiciousIndentation", "UseCompatLoadingForDrawables")
    fun option4Clicked(view:View){
        disableButton()
        if(questionModel.option4 == questionModel.answer){
            binding.option4.background = getDrawable(R.drawable.bg_validate_right)
            correctAns( binding.option4)
        } else {
            wrongAns( binding.option4)
        }
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast?.cancel()
           finish()
        } else {
            backToast = Toast.makeText(baseContext, "ДВОЙНОЕ НАЖАТЕ, ЧТОБЫ ВЫЙТИ ИЗ ИГРЫ", Toast.LENGTH_SHORT)
            backToast?.show()
        }
        backPressedTime = System.currentTimeMillis()
    }

}