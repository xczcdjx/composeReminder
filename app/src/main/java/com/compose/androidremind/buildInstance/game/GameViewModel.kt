package com.compose.androidremind.buildInstance.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.compose.androidremind.data.MAX_NO_OF_WORDS
import com.compose.androidremind.data.SCORE_INCREASE
import com.compose.androidremind.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()
    var guessEnter by mutableStateOf("")
        private set // 仅在类内部修改
    fun updateUserGuess(s: String) = run { guessEnter = s }
    private var usedWords:MutableSet<String> = mutableSetOf()
    private lateinit var currentWord:String
    private fun pickRandomWordAndShuffle():String{
        currentWord= allWords.random()
        return if (usedWords.contains(currentWord)){
            pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            shuffleCurrentWord(currentWord)
        }
    }

    // 打乱当前字符
    private fun shuffleCurrentWord(cur:String): String {
        val tempWord=cur.toCharArray()
        tempWord.shuffle()
        // 检查打乱后字符串是否与输入相同
        while (String(tempWord)==cur){
            tempWord.shuffle()
        }
        return String(tempWord)
    }
    // 检查用户猜测是否正确
    fun checkUserGuess(){
        if (guessEnter.equals(currentWord,ignoreCase = true)){
            val updateScore=_uiState.value.score.plus(SCORE_INCREASE) // 更新分数
            // 更新游戏状态
            updateGameState(updateScore)
        }else{ // 猜错返回true
            _uiState.update {
                it.copy(isGuessedWordWrong = true)
            }
        }
        updateUserGuess("")
    }
    // 更新游戏分数
    private fun updateGameState(updateScore: Int) {
        if (usedWords.size== MAX_NO_OF_WORDS){ // 最大猜单词数
            _uiState.update {
                it.copy(
                    isGuessedWordWrong = false,
                    score = updateScore,
                    isGameOver = true
                )
            }
        }else{
            _uiState.update {
                it.copy(
                    isGuessedWordWrong =false,
                    curScrambleWord = pickRandomWordAndShuffle(),
                    curWordCount = it.curWordCount.inc(), // 回合加一
                    score = updateScore
                )
            }
        }
    }
    // 跳到下一个单词
    fun skipWord(){
        updateGameState(_uiState.value.score)
        updateUserGuess("")
    }
    // 初始化
    init {
        resetGame()
    }

    fun resetGame() {
        usedWords.clear()
        _uiState.value= GameUiState(pickRandomWordAndShuffle())
    }
}