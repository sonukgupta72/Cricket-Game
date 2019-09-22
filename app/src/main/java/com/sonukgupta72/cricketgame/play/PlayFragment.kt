package com.sonukgupta72.cricketgame.play

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.ArrayMap
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.sonukgupta72.cricketgame.*

import kotlinx.android.synthetic.main.play_fragment.*

class PlayFragment : Fragment(), View.OnClickListener{

    private lateinit var viewModel: PlayViewModel
    private var target: Int = 0

    companion object {
        fun newInstance() = PlayFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.play_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        target = (activity as PlayCricketActivity).getTarget()

        viewModel = ViewModelProviders.of(this).get(PlayViewModel::class.java)
        viewModel.setPlayerList()
        viewModel.setDefault(target)


        btn_play.setOnClickListener(this)
        tv_target_score.text = "Target: " +  target

        viewModel.getRandomScoreLiveData().observe(this, Observer {this.setGameScore(it) })

    }

    private fun setGameScore(gameData: GameData) {
        tv_str_player.text = "" + gameData.playerOnStrike?.name
        tv_non_str_player.text = "" + gameData.playerOnNonStrike?.name
        tv_ball_left.text = gameData.ballLeft
        tv_latest_score.text = gameData.scoreByBall
        tv_current_score.text = gameData.currentTotal
        if (gameData.gameOver) {
            gameOver(gameData.resultCode)
        }
    }

    private fun gameOver(result: Int) {
        if (result == Constants.TIE_GAME) {
            Toast.makeText(activity, "Match Tie", Toast.LENGTH_LONG).show()
        } else if (result == Constants.WON_GAME){
            Toast.makeText(activity, "Congratulation! You Won.", Toast.LENGTH_LONG).show()
        } else if (result == Constants.LOSS_GAME){
            Toast.makeText(activity, "Better luck next time.", Toast.LENGTH_LONG).show()
        }

        (activity as PlayCricketActivity).changeFragment(Constants.FRAGMENT_PLAY_AGAIN)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_play -> viewModel.giveRandomNumber()
        }
    }
}
