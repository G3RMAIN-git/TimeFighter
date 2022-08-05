package com.technipixl.timefighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var score = 0

    private lateinit var gameScoreTextView : TextView
    private lateinit var timeLeftTextView: TextView
    private lateinit var tapMeButton: Button

    lateinit var countDownTimer : CountDownTimer
    var initialCountDown : Long = 60000
    var countDownInterval : Long = 1000
    var timeLeft = 60
    var gameStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameScoreTextView = findViewById(R.id.gameScoreTextView)
        timeLeftTextView = findViewById(R.id.timeLeftTextView)
        tapMeButton = findViewById(R.id.tapMeButton)

        /*val newScore = getString(R.string.your_score, score)
        gameScoreTextView.text = newScore*/

        //tapMeButton.setOnClickListener{incrementScore()}

        tapMeButton.setOnClickListener{it ->
            val bounceAnimation = AnimationUtils.loadAnimation(this,R.anim.bounce)
            it.startAnimation(bounceAnimation)
            incrementScore()
        }

        resetGame()
    }

    private fun incrementScore(){

        if (!gameStarted){
            startGame()
        }

        score++

        val newScore = getString(R.string.your_score, score)
        //val newScore ="Your Score: $score"
        gameScoreTextView.text = newScore
    }
     private fun resetGame(){
        score = 0

        val initialScore = getString(R.string.your_score, score)
        gameScoreTextView.text = initialScore

        val initialTimeLeft = getString(R.string.Time_left, 60)
        timeLeftTextView.text = initialTimeLeft
        //var initialCountDown = 0
        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval)  {
            override fun onTick(millisUntilFinished : Long){
                timeLeft = millisUntilFinished.toInt() / 1000

                val timeLeftString = getString(R.string.Time_left,timeLeft)
                timeLeftTextView.text = timeLeftString
            }

            override fun onFinish(){
            endGame()
            }


        }
        gameStarted = false

    }
/*    private fun startTimer(){
        CountDownTimer.start()
    }*/

    private fun startGame(){

        countDownTimer.start()
        gameStarted = true

    }
    private fun endGame(){
        Toast.makeText(this, getString(R.string.game_over_message,score), Toast.LENGTH_LONG).show()
        resetGame()
    }
    companion object{
        private const val SCORE_KEY = "SCORE_KEY"
        private const val TIME_LEFT_KEY = "TIME_LEFT_KEY"
    }
}
