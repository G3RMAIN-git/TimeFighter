package com.technipixl.timefighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
<<<<<<< Updated upstream
=======
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
>>>>>>> Stashed changes


class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

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

        tapMeButton.setOnClickListener{incrementScore()}

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
<<<<<<< Updated upstream
}
=======

    private fun showInfo(){
        val dialogTitle = getString(R.string.about_title,BuildConfig.VERSION_NAME)
        val dialogMessage = getString(R.string.about_message)

        val builder = AlertDialog.Builder(this)
        builder.setTitle(dialogTitle)
        builder.setMessage(dialogMessage)
        builder.create().show()
    }

    companion object{
        private const val SCORE_KEY = "SCORE_KEY"
        private const val TIME_LEFT_KEY = "TIME_LEFT_KEY"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(SCORE_KEY, score)
        outState.putInt(TIME_LEFT_KEY, timeLeft)
        countDownTimer.cancel()

        Log.d(TAG, "onSaveInstanceState : Saving Score: $score & Time left : $timeLeft")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG,"onDestroy called.")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId  == R.id.about_item){
            showInfo()
        }
        return true
    }
}
>>>>>>> Stashed changes
