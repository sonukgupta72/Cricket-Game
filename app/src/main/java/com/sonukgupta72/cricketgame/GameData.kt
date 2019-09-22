package com.sonukgupta72.cricketgame

data class GameData
constructor(val currentTotal: String, val scoreByBall: String, val gameOver: Boolean, val resultCode: Int, val ballLeft: String, val playerOnStrike: PlayerModel?, val playerOnNonStrike: PlayerModel?) {
}