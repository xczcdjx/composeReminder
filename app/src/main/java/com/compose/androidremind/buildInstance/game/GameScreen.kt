package com.compose.androidremind.buildInstance.game

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.androidremind.R
import com.compose.androidremind.ui.theme.AndroidRemindTheme

@Composable
fun GameScreen(gameViewModel: GameViewModel = viewModel()) {
//    val gameUiState=gameViewModel.uiState.collectAsState().value
    val gameUiState by gameViewModel.uiState.collectAsState()
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("猜谜", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(10.dp))
        GameLayout(wordCount = gameUiState.curWordCount, curText = gameUiState.curScrambleWord,
            guessEnter = gameViewModel.guessEnter,
            onUserGuessChanged = {
                gameViewModel.updateUserGuess(it)
            },
            isGuessError = gameUiState.isGuessedWordWrong,
            onKeyboardDone = { gameViewModel.checkUserGuess() }
        )
        Spacer(modifier = Modifier.height(10.dp))
        // submit
        Button(onClick = { gameViewModel.checkUserGuess() }, modifier = Modifier.fillMaxWidth().padding(5.dp)) {
            Text(
                text = stringResource(R.string.submit),
                fontSize = 16.sp
            )
        }
        // skip
        OutlinedButton(onClick = { gameViewModel.skipWord() }, modifier = Modifier.fillMaxWidth().padding(5.dp)) {
            Text(
                text = stringResource(R.string.skip),
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        // 游戏得分
        GameScore(score = gameUiState.score, modifier = Modifier.padding(20.dp))

        // 结算弹出层
        if (gameUiState.isGameOver) {
            FinalScoreDialog(gameUiState.score) {
                gameViewModel.resetGame()
            }
        }
    }
}

@Composable
fun GameScore(score: Int, modifier: Modifier) {
    Card {
        Text(
            text = stringResource(R.string.score, score),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun GameLayout(
    wordCount: Int, curText: String, guessEnter: String,
    onUserGuessChanged: (String) -> Unit,
    isGuessError: Boolean,
    onKeyboardDone: () -> Unit
) {
    Card(elevation = CardDefaults.cardElevation(5.dp)) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                modifier = Modifier.align(alignment = Alignment.End),
                text = stringResource(id = R.string.word_count, wordCount),
                style = MaterialTheme.typography.titleMedium
            )
            // 当前猜词
            Text(
                text = curText,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            // 提示文本
            Text(
                text = stringResource(id = R.string.instructions),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
            // 输入框
            KeyBoardInput(
                guessEnter = guessEnter,
                onUserGuessChanged = onUserGuessChanged,
                isGuessError = isGuessError,
            ) {
                onKeyboardDone()
            }
        }
    }
}

@Composable
fun FinalScoreDialog(
    score:Int,
    onPlayAgain: () -> Unit
) {
    val activity = LocalContext.current as Activity
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = stringResource(id= R.string.congratulations)) },
        text = { Text(text = stringResource(id= R.string.your_score,score)) },
        confirmButton = {
        TextButton(onClick = onPlayAgain) { Text(text = stringResource(id = R.string.play_again)) }
    },
        dismissButton = {
            TextButton(onClick = { activity.finish() }) { Text(text = stringResource(id = R.string.exit)) }
        })
}

@Preview()
@Composable
fun Prev() {
    AndroidRemindTheme {
        GameScreen()
    }
}