package com.phatnv.widgets.presentation.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.phatnv.widgets.R
import com.phatnv.widgets.theme.AppTheme
import com.phatnv.widgets.utils.Validation

@ExperimentalComposeUiApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage() {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val (emailRef, passwordRef) = FocusRequester.createRefs()
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var showPassword by remember {
        mutableStateOf(false)
    }

    fun onDone() {
        keyboardController?.hide()
        focusManager.clearFocus()
        Toast.makeText(context, "On Login", Toast.LENGTH_SHORT).show()
    }
    Scaffold {
        Column(
            modifier = Modifier
                .padding(horizontal = AppTheme.dimensions.paddingLarge)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier = Modifier
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        layout(placeable.width, placeable.height) {
                            placeable.place(0, 0)
                        }
                    }
                    .padding(bottom = 2 * AppTheme.dimensions.paddingLarge)
                    .size(100.dp)
                    .testTag("circle"),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                ),
            ) {
                Image(
                    painterResource(id = R.drawable.chair),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    colorFilter = ColorFilter.tint(Color.Red)
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(emailRef)
                    .focusProperties {
                        next = passwordRef
                    },
                label = {
                    Text(text = "Email")
                },
                placeholder = {
                    Text(stringResource(id = R.string.email_placeholder))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                ),
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Email, contentDescription = "Email Icon")
                },
                singleLine = true,
                value = email,
                onValueChange = { newEmail ->
                    email = newEmail
                },
            )
            Spacer(modifier = Modifier.height(AppTheme.dimensions.paddingMedium))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(passwordRef)
                    .focusProperties {
                        previous = emailRef
                    },
                label = {
                    Text(text = "Password")
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
                singleLine = true,
                value = password,
                onValueChange = { newPassword ->
                    password = newPassword
                },
            )
            if (email.isNotEmpty()) {
                if (Validation.isValidEmail(email)) {
                    Text(text = "Email is valid", color = Color.Blue)
                } else {
                    Text(text = "Email is not valid", color = Color.Red)
                }
            }
            Spacer(modifier = Modifier.height(AppTheme.dimensions.paddingLarge))
            Button(
                onClick = {
                    onDone()
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(it)
            ) {
                Text(text = "Login")
            }
        }
    }
}

@Preview
@ExperimentalComposeUiApi
@Composable
fun LoginPreview() {
    LoginPage()
}


