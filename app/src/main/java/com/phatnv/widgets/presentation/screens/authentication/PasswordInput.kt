package com.phatnv.widgets.presentation.screens.authentication

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.phatnv.widgets.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    password: String?,
    onPasswordChanged: (password: String) -> Unit,
    onDone: () -> Unit,
) {
    var showPassword by remember {
        mutableStateOf(false)
    }
    TextField(
        modifier = modifier,
        value = password ?: "",
        onValueChange = { password ->
            onPasswordChanged(password)
        },
        label = {
            Text(text = stringResource(id = R.string.label_password))
        },
        placeholder = {
            Text(stringResource(id = R.string.password_placeholder))
        },
        leadingIcon = {
            Icon(imageVector = Icons.Outlined.Lock, contentDescription = "Lock Icon")
        },
        trailingIcon = {
            IconButton(onClick = { showPassword = !showPassword }) {
                Icon(
                    imageVector = if (showPassword) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                    contentDescription = "Password Visible",
                )
            }
        },
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDone()
            }
        ),
    )
}