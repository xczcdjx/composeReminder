package com.compose.androidremind.buildInstance.game

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.compose.androidremind.R

@Composable
fun KeyBoardInput(
    guessEnter: String,
    onUserGuessChanged: (String) -> Unit,
    isGuessError:Boolean,
    onKeyboardDone:()->Unit
) {
    OutlinedTextField(
        value = guessEnter,
        singleLine = true,
        onValueChange = onUserGuessChanged,
        shape = shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = {
            if(isGuessError) Text(text = stringResource(id= R.string.wrong_guess))
            else Text(text = stringResource(id= R.string.enter_your_word))
        },
        isError = isGuessError,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = { onKeyboardDone() })
    )
}
