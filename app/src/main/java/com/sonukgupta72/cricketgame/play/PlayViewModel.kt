package com.sonukgupta72.cricketgame.play

import android.util.ArrayMap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sonukgupta72.cricketgame.Constants
import com.sonukgupta72.cricketgame.GameData
import com.sonukgupta72.cricketgame.PlayerModel
import java.util.*

class PlayViewModel : ViewModel() {

    private var gameScore: MutableLiveData<GameData> = MutableLiveData()


    private var currentTotalScore: Int = 0
    private var wicket: Int = 0
    private var target: Int = 0
    private var ball: Int = 0
    private var over: Int = 0
    private var resultCode: Int = Constants.CONTINUE_GAME
    private var gameOver: Boolean = false
    private var playerOnStrike: Int = 1
    private var playerOnNonStrike: Int = 2
    private var playerList: ArrayMap<Int, PlayerModel> = ArrayMap()



    fun setPlayerList() {
        playerList.put(1, PlayerModel("P1", 0))
        playerList.put(2, PlayerModel("P2", 0))
        playerList.put(3, PlayerModel("P3", 0))
        playerList.put(4, PlayerModel("P4", 0))
        playerList.put(5, PlayerModel("P5", 0))
    }

    fun giveRandomNumber() {
        val random = Random()
        score(random.nextInt(8 - 0))
    }

    fun getRandomScoreLiveData(): LiveData<GameData> {
        return gameScore
    }

    private fun score(it: Int?) {

        var latestRun = ""
        when (it) {
            0-> {
                latestRun += it
                ball++
            }
            1-> {
                latestRun += it
                currentTotalScore += it
                changeSide()
                ball++
            }
            2-> {
                latestRun += it
                currentTotalScore += it
                ball++
            }
            3-> {
                latestRun += it
                currentTotalScore += it
                changeSide()
                ball++
            }
            4-> {
                latestRun += it
                currentTotalScore += it
                ball++
            }
            5-> {
                latestRun += it
                currentTotalScore += it
                changeSide()
                ball++
            }
            6-> {
                latestRun += it
                currentTotalScore += it
                ball++
            }
            7-> {
                latestRun += "W"
                wicket++
                ball++
                setNewPlayerOnStrike()
            }
            8-> {
                latestRun += "NB"
                currentTotalScore += 1
            }
        }

        if (ball % 6 == 0) {
            over++
            ball = 0
            changeSide()
        }

        if (currentTotalScore > target) {
            gameOver = true
            resultCode = Constants.WON_GAME
        } else if ((over >= 5
                    || wicket >= 4)
            && currentTotalScore == target) {
            gameOver = true
            resultCode = Constants.TIE_GAME
        } else if (over >= 5
            || wicket >= 4) {
            gameOver = true
            resultCode = Constants.LOSS_GAME
        }

        gameScore.value = GameData("$currentTotalScore-$wicket", latestRun, gameOver, resultCode, "$over.$ball/5.0",  playerList.get(playerOnStrike), playerList.get(playerOnNonStrike))
    }

    private fun changeSide() {
        var temp = playerOnStrike
        playerOnStrike = playerOnNonStrike
        playerOnNonStrike = temp
    }

    private fun setNewPlayerOnStrike() {
        if (wicket < 4) {
            playerOnStrike = getNewPlayerOnStrike(playerOnStrike)
        } else {
            playerOnStrike = 0
        }
    }

    private fun getNewPlayerOnStrike(sp : Int): Int {
        playerList.remove(sp)
        var np = sp + 1 //new player

        while (playerList.get(np) == null || np == playerOnNonStrike ) {
            np++
        }

        return np
    }

    fun setDefault(target: Int) {
        this.target = target
        var ps: PlayerModel? = playerList.get(playerOnStrike)
        var pns: PlayerModel? = playerList.get(playerOnNonStrike)
        gameScore.value = GameData("0-0", "-",false, Constants.CONTINUE_GAME, "0.0/5.0", ps, pns)
    }
}
