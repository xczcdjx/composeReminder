package com.compose.androidremind.buildInstance.game

data class GameUiState(
    val curScrambleWord: String = "", // 当前被混淆得单词
    val curWordCount: Int = 1, // 当前单词数
    val score: Int = 0, // 得分
    val isGuessedWordWrong: Boolean = false, // 猜测单词是否错误
    val isGameOver:Boolean=false // 游戏是否结束
)
