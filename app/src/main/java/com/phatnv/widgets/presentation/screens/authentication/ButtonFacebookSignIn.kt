package com.phatnv.widgets.presentation.screens.authentication

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.common.api.ApiException
import com.phatnv.widgets.R

@Composable
fun ButtonFacebookSignIn(
    onFacebookSignInCompleted: (String) -> Unit,
    onError: (ApiException) -> Unit,
) {
    val context = LocalContext.current
    val callbackManager = remember { CallbackManager.Factory.create() }

    val startForActivityResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val resultCode = result.resultCode
            val data = result.data
            callbackManager.onActivityResult(resultCode, resultCode, data)
        }

    val callback = object : FacebookCallback<LoginResult> {
        override fun onSuccess(result: LoginResult) {
            onFacebookSignInCompleted(result.accessToken.token)
        }

        override fun onCancel() {
            // Handle login cancellation
        }

        override fun onError(error: FacebookException) {
            // Handle login error
        }
    }

    LoginManager.getInstance().registerCallback(callbackManager, callback)

    DisposableEffect(callbackManager) {
        onDispose {
            LoginManager.getInstance().unregisterCallback(callbackManager)
        }
    }

    Button(
        onClick = {
            try {
                LoginManager.getInstance().logInWithReadPermissions(
                    context as androidx.activity.ComponentActivity,
                    listOf("email")
                )
                startForActivityResult.launch(null)
            } catch (e: ApiException) {
                onError(e)
            }
        },
        modifier = Modifier
            .padding(32.dp, 0.dp, 32.dp, 8.dp)
            .fillMaxWidth()
            .height(45.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(Color.White),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_facebook),
                contentDescription = "Facebook icon",
                tint = Color.Unspecified,
            )
            Text(
                text = "Access using Facebook",
                color = Color.Black,
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}