package com.sonukgupta72.cricketgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sonukgupta72.cricketgame.play.PlayFragment
import com.sonukgupta72.cricketgame.playAgain.PlayAgainFragment
import com.sonukgupta72.cricketgame.target.TargetFragment

class PlayCricketActivity : AppCompatActivity() {

    private var target : Int = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_cricket)

        changeFragment(Constants.FRAGMENT_TARGET_SCORE)
    }

    fun changeFragment(reqFragment: Int) {
        when(reqFragment) {
            Constants.FRAGMENT_TARGET_SCORE ->
                supportFragmentManager.beginTransaction().replace(R.id.content, TargetFragment.newInstance()).commit()
            Constants.FRAGMENT_PLAY_GAME ->
                supportFragmentManager.beginTransaction().replace(R.id.content, PlayFragment.newInstance()).commit()
            Constants.FRAGMENT_PLAY_AGAIN ->
                supportFragmentManager.beginTransaction().replace(R.id.content, PlayAgainFragment.newInstance()).commit()

        }
    }

    fun getTarget() : Int {
        return target
    }

    fun setTarget(t: Int) {
        target = t
    }
}
