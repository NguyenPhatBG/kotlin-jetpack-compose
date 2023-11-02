package com.phatnv.widgets.presentation.screens.authentication

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.phatnv.widgets.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    email: String?,
    onEmailChanged: (email: String) -> Unit,
    onNext: () -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        value = email ?: "",
        onValueChange = { email ->
            onEmailChanged(email)
        },
        label = {
            Text(text = stringResource(id = R.string.label_email))
        },
        placeholder = {
            Text(stringResource(id = R.string.email_placeholder))
        },
        singleLine = true,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Email, contentDescription = null)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                onNext()
            }
        ),
    )
}